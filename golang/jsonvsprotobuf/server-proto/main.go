package main

import (
	"io/ioutil"
	"log"
	"net/http"
	_ "net/http/pprof"

	"github.com/golang/protobuf/proto"
	"github.com/gorilla/mux"
	pb "github.com/mateuszdyminski/grpc/golang/jsonvsprotobuf/proto"
)

func main() {
	r := mux.NewRouter()
	r.HandleFunc("/user", UserHandler).Methods("POST")

	// user http server
	http.ListenAndServe(":10001", r)
}

func UserHandler(w http.ResponseWriter, r *http.Request) {
	data, err := ioutil.ReadAll(r.Body)
	if err != nil {
		log.Println(err)
		w.WriteHeader(http.StatusInternalServerError)
		return
	}

	var user pb.User
	err = proto.Unmarshal(data, &user)
	if err != nil {
		log.Println(err)
		w.WriteHeader(http.StatusInternalServerError)
		return
	}
}
