package main

import (
	"flag"
	"log"
	"net"
	_ "net/http/pprof"
	"strings"
	"sync"
	"net/http"
	"fmt"
	"io"

	pb "github.com/mateuszdyminski/grpc/golang/search"
	"golang.org/x/net/context"
	"google.golang.org/grpc"
)

var (
	backends = flag.String("backends", "localhost:36061,localhost:36062", "comma-separated backend server addresses")
)

type server struct {
	backends []pb.SearchEngineClient
}

// Search issues Search RPCs in parallel to the backends and returns
// the first result.
func (s *server) Search(ctx context.Context, req *pb.Request) (*pb.Result, error) { // HL
	c := make(chan result, len(s.backends))
	for _, b := range s.backends {
		go func(backend pb.SearchEngineClient) { // HL
			res, err := backend.Search(ctx, req) // HL
			c <- result{res, err}                // HL
		}(b) // HL
	}
	first := <-c                // HL
	return first.res, first.err // HL
}

type result struct {
	res *pb.Result
	err error
}

// Watch runs Watch RPCs in parallel on the backends and returns a
// merged stream of results.
func (s *server) Watch(req *pb.Request, stream pb.SearchEngine_WatchServer) error { // HL
	ctx := stream.Context()
	c := make(chan result) // HL
	var wg sync.WaitGroup
	for _, b := range s.backends {
		wg.Add(1)
		go func(backend pb.SearchEngineClient) { // HL
			defer wg.Done()                    // HL
			watchBackend(ctx, backend, req, c) // HL
		}(b) // HL
	}
	go func() {
		wg.Wait()
		close(c) // HL
	}()
	for res := range c { // HL
		if res.err != nil {
			return res.err
		}
		if err := stream.Send(res.res); err != nil { // HL
			return err // HL
		} // HL
	}
	return nil
}

// watchBackend runs Watch on a single backend and sends results on c.
// watchBackend returns when ctx.Done is closed or stream.Recv fails.
func watchBackend(ctx context.Context, backend pb.SearchEngineClient, req *pb.Request, c chan<- result) {
	stream, err := backend.Watch(ctx, req) // HL
	if err != nil {
		select {
		case c <- result{err: err}: // HL
		case <-ctx.Done():
		}
		return
	}
	for {
		res, err := stream.Recv() // HL
		select {
		case c <- result{res, err}: // HL
			if err != nil {
				return
			}
		case <-ctx.Done():
			return
		}
	}
}

// BiWatch runs Watch RPCs in parallel on the backends and returns a
// merged stream of results.
func (s *server) BiWatch(stream pb.SearchEngine_BiWatchServer) error { // HL
	ctx := stream.Context()
	c := make(chan result) // HL
	searches := make([]chan pb.Request, 0)
	searches = append(searches, make(chan pb.Request))
	searches = append(searches, make(chan pb.Request))

	done := make(chan bool) // HL
	go func() {
		for {
			in, err := stream.Recv()
			if err == io.EOF {
				done <- true
				return
			}
			if err != nil {
				done <- true
				return
			}

			for i := range searches {
				searches[i] <- *in
			}
		}
	}()

	var wg sync.WaitGroup
	for i, b := range s.backends {
		wg.Add(1)
		go func(backend pb.SearchEngineClient, index int) { // HL
			defer wg.Done() // HL
			fmt.Printf("Start watching to channel %d \n", &searches[i])
			watchBiBackend(ctx, backend, searches[index], c) // HL
		}(b, i) // HL
	}
	go func() {
		wg.Wait()
		close(c) // HL
	}()
	for res := range c { // HL
		if res.err != nil {
			return res.err
		}
		if err := stream.Send(res.res); err != nil { // HL
			return err // HL
		} // HL
	}
	return nil
}

// watchBiBackend runs Watch on a single backend and sends results on c.
// watchBiBackend returns when ctx.Done is closed or stream.Recv fails.
func watchBiBackend(ctx context.Context, backend pb.SearchEngineClient, searchFor chan pb.Request, c chan result) {
	fmt.Printf("Start watching from channel %d \n", &searchFor)
	stream, err := backend.BiWatch(ctx) // HL
	if err != nil {
		select {
		case c <- result{err: err}: // HL
		case <-ctx.Done():
		}
		return
	}

	done := make(chan bool)
	go func() {
		for {
			res, err := stream.Recv() // HL
			select {
			case c <- result{res, err}:
				if err != nil {
					done <- true
					return
				}
			case <-ctx.Done():
				done <- true
				return
			}
		}
	}()

	go func() {
		for {
			select {
			case req := <-searchFor:
				err := stream.Send(&req)
				if err != nil {
					done <- true
					return
				}
			}
		}
	}()

	<-done
	return
}

func main() {
	flag.Parse()
	grpc.EnableTracing = true
	go http.ListenAndServe(":36660", nil)   // HTTP debugging
	lis, err := net.Listen("tcp", ":36060") // RPC port
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	s := new(server)
	for _, addr := range strings.Split(*backends, ",") {
		conn, err := grpc.Dial(addr, grpc.WithInsecure())
		if err != nil {
			log.Fatalf("fail to dial: %v", err)
		}
		client := pb.NewSearchEngineClient(conn)
		s.backends = append(s.backends, client)
	}

	g := grpc.NewServer()
	pb.RegisterSearchEngineServer(g, s)
	fmt.Println("Frontend started at port 36660")
	g.Serve(lis)
}
