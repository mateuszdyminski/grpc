package main

import (
	"flag"
	"fmt"
	"io"
	"log"
	"time"
	"math/rand"

	"golang.org/x/net/context"
	pb "github.com/mateuszdyminski/grpc/golang/search"
	"google.golang.org/grpc"
)

var (
	server = flag.String("server", "localhost:36060", "server address")
	mode = flag.String("mode", "search", `one of "search" or "watch"`)
	query = flag.String("query", "test", "query string")
)

func main() {
	flag.Parse()

	// Connect to the server.
	conn, err := grpc.Dial(*server, grpc.WithInsecure()) // HL
	if err != nil {
		log.Fatalf("fail to dial: %v", err)
	}
	defer conn.Close()
	client := pb.NewGoogleClient(conn) // HL

	// Run the RPC.
	switch *mode {
	case "search":
		search(client, *query) // HL
	case "watch":
		watch(client, *query)
	case "biwatch":
		biwatch(client, *query)
	default:
		log.Fatalf("unknown mode: %q", *mode)
	}
}

// search issues a search for query and prints the result.
func search(client pb.GoogleClient, query string) {
	ctx, cancel := context.WithTimeout(context.Background(), 80 * time.Millisecond) // HL
	defer cancel()
	req := &pb.Request{Query: query}    // HL
	res, err := client.Search(ctx, req) // HL
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println(res) // HL
}

// watch runs a Watch RPC and prints the result stream.
func watch(client pb.GoogleClient, query string) {
	ctx, cancel := context.WithCancel(context.Background())
	defer cancel()
	req := &pb.Request{Query: query}      // HL
	stream, err := client.Watch(ctx, req) // HL
	if err != nil {
		log.Fatal(err)
	}
	for {
		res, err := stream.Recv() // HL
		if err == io.EOF {        // HL
			fmt.Println("and now your watch is ended")
			return
		}
		if err != nil {
			log.Fatal(err)
		}
		fmt.Println(res) // HL
	}
}

// watch runs a Watch RPC and prints the result stream.
func biwatch(client pb.GoogleClient, query string) {
	ctx, cancel := context.WithCancel(context.Background())
	defer cancel()
	stream, err := client.BiWatch(ctx) // HL
	if err != nil {
		log.Fatal(err)
	}

	done := make(chan bool)
	go func(done chan bool) {
		for {
			res, err := stream.Recv() // HL
			if err == io.EOF {        // HL
				fmt.Println("and now your watch is ended")
				done <- true
				return
			}
			if err != nil {
				log.Fatal(err)
			}
			fmt.Println(res) // HL
		}
	}(done)

	send(stream, query)
	for {
		d := randomDuration(10 * time.Second)
		select {
		case <-time.After(d):
			send(stream, query)
		case <-done:
			fmt.Println("ending")
		}
	}
}

func send(stream pb.Google_BiWatchClient, query string) {
	if err := stream.Send(&pb.Request{Query: query + time.Now().Format("2006-01-02 15:04:05")}); err != nil {
		log.Fatal(err)
	}
}

// randomDuration returns a random duration up to max, at intervals of max/10.
func randomDuration(max time.Duration) time.Duration {
	return time.Duration(1 + int64(rand.Intn(10))) * (max / 10)
}