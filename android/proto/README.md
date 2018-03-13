### Requirements

* Protobuf github.com/google/protobuf
* Golang protoc wrapper (go get github.com/golang/protobuf/protoc-gen-go)

```
protoc ./load.proto --go_out=plugins=grpc:../load
```
