package main

import (
	"flag"
	"fmt"
	"log"
	"net"

	"time"

	pb "github.com/mateuszdyminski/grpc/android/load"
	"golang.org/x/net/context"
	"google.golang.org/grpc"
)

var port = flag.Int("port", 11002, "RPC port")

type server struct{}

// Load returns specified number of users.
func (s *server) Load(ctx context.Context, req *pb.Request) (*pb.Result, error) {
	start := time.Now()
	users := make([]*pb.Result_User, 0)
	for i := int32(0); i < req.BatchSize; i++ {
		users = append(users, generateUser())
	}

	fmt.Printf("Returned %d users! Took: %v \n", req.BatchSize, time.Now().Sub(start))

	return &pb.Result{Users: users}, nil
}

// LoadStream returns a stream of results where a single result is a list of users.
func (s *server) LoadStream(req *pb.Request, stream pb.Load_LoadStreamServer) error {
	for i := int32(0); i < req.Total; i++ {
		users := make([]*pb.Result_User, 0)
		for i := int32(0); i < req.BatchSize; i++ {
			users = append(users, generateUser())
		}

		if err := stream.Send(&pb.Result{Users: users}); err != nil {
			return err
		}
	}

	return nil
}

func main() {
	flag.Parse()

	lis, err := net.Listen("tcp", fmt.Sprintf(":%d", *port))
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}

	g := grpc.NewServer()
	pb.RegisterLoadServer(g, new(server))
	g.Serve(lis)
}

func generateUser() *pb.Result_User {
	user := pb.Result_User{
		FirstName:  "Jan",
		LastName:   "Kowalski",
		BirthDay:   1464859931,
		Phone:      "71-333-33-33",
		Gender:     2,
		IsEmployed: true,
		Salary:     1000.0,
		Bio:        "Handsome, biking etc",
	}

	for i := 0; i <= 100; i++ {
		user.WebSites = append(user.WebSites,
			&pb.Result_User_WebSite{
				Url:      "",
				Title:    "",
				Snippets: []string{"Some snippet 1", "Some snippet 2", "Some snippet 3", "Some snippet 4"},
			})
	}

	return &user
}
