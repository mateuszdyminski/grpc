package main

import (
	"encoding/json"
	"io/ioutil"
	"log"
	"net/http"
	_ "net/http/pprof"

	"github.com/gorilla/mux"
	pb "github.com/mateuszdyminski/grpc/golang/jsonvsprotobuf/proto"
)

func main() {
	r := mux.NewRouter()
	r.HandleFunc("/user", UserHandler).Methods("POST")

	// pprof http server
	go func() {
		log.Println(http.ListenAndServe("localhost:6060", nil))
	}()

	// user http server
	http.ListenAndServe(":10000", r)
}

func UserHandler(w http.ResponseWriter, r *http.Request) {
	data, err := ioutil.ReadAll(r.Body)
	if err != nil {
		log.Println(err)
		w.WriteHeader(http.StatusInternalServerError)
		return
	}

	var user pb.User
	err = json.Unmarshal(data, &user)
	if err != nil {
		log.Println(err)
		w.WriteHeader(http.StatusInternalServerError)
		return
	}
}
