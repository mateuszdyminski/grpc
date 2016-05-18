### gRPC Presentation

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
