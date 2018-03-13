package main

import (
	"bytes"
	"encoding/json"
	"flag"
	"log"
	"net/http"
	"time"

	"fmt"

	"github.com/golang/protobuf/proto"
	pb "github.com/mateuszdyminski/grpc/golang/jsonvsprotobuf/proto"
)

func protoBody() ([]byte, error) {
	user := generateUser()
	return proto.Marshal(&user)
}

func jsonBody() ([]byte, error) {
	user := generateUser()
	return json.Marshal(&user)
}

func generateUser() pb.User {
	user := pb.User{
		FirstName:  "Jan",
		LastName:   "Kowalski",
		BirthDay:   1464859931,
		Phone:      "71-333-33-33",
		Gender:     2,
		IsEmployed: true,
		Salary:     1000.0,
		Bio:        "Handsome, biking etc",
	}

	for i := 0; i <= 1000; i++ {
		user.WebSites = append(user.WebSites,
			&pb.User_WebSite{
				Url:      "",
				Title:    "",
				Snippets: []string{"Some snippet 1", "Some snippet 2", "Some snippet 3", "Some snippet 4"},
			})
	}

	return user
}

func main() {
	format := flag.String("format", "json", "The encoding to use. json or proto")
	duration := flag.Int("duration", 10, "Duration to run in seconds.")
	flag.Parse()

	var data []byte
	var err error
	var port int

	switch *format {
	case "json":
		data, err = jsonBody()
		port = 10000
	case "proto":
		data, err = protoBody()
		port = 10001
	}

	if err != nil {
		log.Fatalf("Failed to create request body: %v", err)
	}

	log.Printf("Sending requests for %d seconds using %s encoding. Request size: %d\n", *duration, *format, len(data))

	requestTotal := 0
	go func() {
		for {
			resp, err := http.Post(fmt.Sprintf("http://127.0.0.1:%d/user", port), "", bytes.NewReader(data))
			if err != nil {
				log.Fatalf("Failed http request: %v", err)
			}
			if resp.StatusCode != http.StatusOK {
				log.Fatalf("Error response code: %v", resp.StatusCode)
			}
			requestTotal++
		}
	}()

	time.Sleep(time.Duration(*duration) * time.Second)
	log.Printf("Request Total: %d\n", requestTotal)
}
