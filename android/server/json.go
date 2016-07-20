package main

import (
	"encoding/json"
	"log"
	"net/http"

	"fmt"
	"time"

	"github.com/gorilla/mux"
	pb "github.com/mateuszdyminski/grpc/android/load"
)

func main() {
	r := mux.NewRouter()
	r.HandleFunc("/load", handler).Methods("POST")

	// user http server
	log.Fatal(http.ListenAndServe(":11001", r))
}

func handler(w http.ResponseWriter, req *http.Request) {
	start := time.Now()
	pbReq := new(pb.Request)
	if err := json.NewDecoder(req.Body).Decode(pbReq); err != nil {
		http.Error(w, "can't decode json request", http.StatusInternalServerError)
		return
	}

	users := make([]*pb.Result_User, 0)
	for i := int32(0); i < pbReq.BatchSize; i++ {
		users = append(users, generateJsonUser())
	}

	w.Header().Set("Content-Type", "application/json; charset=utf-8")
	jsonData, err := json.Marshal(pb.Result{users})
	if err != nil {
		http.Error(w, "can't encode json", http.StatusInternalServerError)
		return
	}

	if _, err := w.Write(jsonData); err != nil {
		http.Error(w, "can't write json", http.StatusInternalServerError)
		return
	}

	fmt.Printf("Returned %d users! Took: %v \n", pbReq.BatchSize, time.Now().Sub(start))
}

func generateJsonUser() *pb.Result_User {
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
