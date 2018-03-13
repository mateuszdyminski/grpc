### JSON vs ProtocolBuffers


#### Run JSON http server

```
cd server-json && go run main.go
```

#### Run ProtocolBuffer http server

```
cd server-proto && go run main.go
```

#### Run JSON client
```
go run client.go
```

#### Run ProtocolBuffer client
```
go run client.go -format=proto
```

#### Run pprof analysis
CPU
```
go tool pprof http://localhost:6060/debug/pprof/profile
```

Mem
```
go tool pprof http://localhost:6060/debug/pprof/heap
```
