## gRPC Presentations

There are 2 version of this presentation

- old - based on Go present tool - see below
- new - based on PowerPoint - [see](new/grpc-code-dive.pptx)

### Old

#### Requirements

* Installed Golang
* Installed Present tool (go get golang.org/x/tools/cmd/present)

#### Run
```
present
```

Go to: http://127.0.0.1:3999/grpc.slide


#### Description in Polish

gRPC to framework RPC, który rozwiązuje wiele problemów występujących w środowiskach rozproszonych, takich jak: śledzenie żądań, anulowanie żądań w trakcie ich trwania, wersjonowanie API, kompresja przesyłanych danych czy obustronne strumieniowanie danych(klient-serwer). gRPC został stworzony w Google jako następca wewnętrznej platformy RPC - Stubby oraz udostępniony jako otwarte oprogramowanie. W tej chwili gRPC posiada biblioteki w 10 różnych językach: C, C++, Java, Go, Node.js, Python, Ruby, Objective-C, PHP oraz C#. Oparty jest na protocole HTTP/2.

W prezentacji pokaże, jak przy pomocy gRPC stworzyć prosty klaster mikroserwisów imitujący wyszukiwarkę Google. Przejdziemy po kolei od tworzenia opisów serwisów w języku IDL(Protocol Buffers) do implementacji klienta oraz serwera przy pomocy gRPC. Pokaże jak śledzić rozproszone wywołania w naszym klastrze oraz zaprezentuję strumieniowanie danych między klientem, a serwerem. Przykłady poszczególnych mikroserwisów będą w Javie oraz Go(Golang). Nie obędzie się również bez dema.        

Jeżeli masz już dosyć nietechnicznych prezentacji o mikroserwisach, w których poza diagramami oraz architekturą mało jest konkretów ta prezentacja przypadnie Ci do gustu. Będzie działający na żywo system oraz dużo kodu w Javie oraz Go.  

Więcej na: http://www.grpc.io/

#### Short Description in Polish

gRPC to framework do obsługi zdalnych wywołań procedur stworzony przez Google. Zobacz jak przy jego pomocy zbudować wydajne i skalowalne API umożliwiające m.in śledzenie i anulowanie żądań, wersjonowanie czy strumieniowanie danych między klientem, a serwerem. Przykłady serwisów będą w Javie oraz Go.

#### Description in English

gRPC is a RPC framework focused on performance and accessibility across a wide range of programming languages. gRPC was initially developed at Google as a next generation of Stubby — a general application platform at the heart of many Google products and services. gRPC is not only successor of Stubby, but improve upon it, in the open, around modern standards such as HTTP/2 and Protocol Buffers. If you ever wondering how google is creating the microservices this talk is definitely for you. 

In this session I will demonstrate, through a series of live demos and code walkthroughs, how to design and build a collection of microservices using gRPC from the ground up. Examples are written in Java and Go. At the end I will show how we can use gRPC in backend-device{Android, iOS} communication with simple demo app.

More at: http://www.grpc.io/