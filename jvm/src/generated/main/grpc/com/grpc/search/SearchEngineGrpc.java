package com.grpc.search;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.7.0)",
    comments = "Source: search.proto")
public final class SearchEngineGrpc {

  private SearchEngineGrpc() {}

  public static final String SERVICE_NAME = "search.SearchEngine";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.grpc.search.Request,
      com.grpc.search.Result> METHOD_SEARCH =
      io.grpc.MethodDescriptor.<com.grpc.search.Request, com.grpc.search.Result>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "search.SearchEngine", "Search"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.grpc.search.Request.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.grpc.search.Result.getDefaultInstance()))
          .setSchemaDescriptor(new SearchEngineMethodDescriptorSupplier("Search"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.grpc.search.Request,
      com.grpc.search.Result> METHOD_WATCH =
      io.grpc.MethodDescriptor.<com.grpc.search.Request, com.grpc.search.Result>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "search.SearchEngine", "Watch"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.grpc.search.Request.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.grpc.search.Result.getDefaultInstance()))
          .setSchemaDescriptor(new SearchEngineMethodDescriptorSupplier("Watch"))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.grpc.search.Request,
      com.grpc.search.Result> METHOD_BI_WATCH =
      io.grpc.MethodDescriptor.<com.grpc.search.Request, com.grpc.search.Result>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
          .setFullMethodName(generateFullMethodName(
              "search.SearchEngine", "BiWatch"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.grpc.search.Request.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.grpc.search.Result.getDefaultInstance()))
          .setSchemaDescriptor(new SearchEngineMethodDescriptorSupplier("BiWatch"))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SearchEngineStub newStub(io.grpc.Channel channel) {
    return new SearchEngineStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SearchEngineBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SearchEngineBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SearchEngineFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SearchEngineFutureStub(channel);
  }

  /**
   */
  public static abstract class SearchEngineImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Search returns a Search Engine result for the query.
     * </pre>
     */
    public void search(com.grpc.search.Request request,
        io.grpc.stub.StreamObserver<com.grpc.search.Result> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_SEARCH, responseObserver);
    }

    /**
     * <pre>
     * Watch returns a stream of Google search results for the query.
     * </pre>
     */
    public void watch(com.grpc.search.Request request,
        io.grpc.stub.StreamObserver<com.grpc.search.Result> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_WATCH, responseObserver);
    }

    /**
     * <pre>
     * BiWatch returns a stream of Google search results for the stream of queries.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.grpc.search.Request> biWatch(
        io.grpc.stub.StreamObserver<com.grpc.search.Result> responseObserver) {
      return asyncUnimplementedStreamingCall(METHOD_BI_WATCH, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_SEARCH,
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.search.Request,
                com.grpc.search.Result>(
                  this, METHODID_SEARCH)))
          .addMethod(
            METHOD_WATCH,
            asyncServerStreamingCall(
              new MethodHandlers<
                com.grpc.search.Request,
                com.grpc.search.Result>(
                  this, METHODID_WATCH)))
          .addMethod(
            METHOD_BI_WATCH,
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.grpc.search.Request,
                com.grpc.search.Result>(
                  this, METHODID_BI_WATCH)))
          .build();
    }
  }

  /**
   */
  public static final class SearchEngineStub extends io.grpc.stub.AbstractStub<SearchEngineStub> {
    private SearchEngineStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SearchEngineStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SearchEngineStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SearchEngineStub(channel, callOptions);
    }

    /**
     * <pre>
     * Search returns a Search Engine result for the query.
     * </pre>
     */
    public void search(com.grpc.search.Request request,
        io.grpc.stub.StreamObserver<com.grpc.search.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SEARCH, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Watch returns a stream of Google search results for the query.
     * </pre>
     */
    public void watch(com.grpc.search.Request request,
        io.grpc.stub.StreamObserver<com.grpc.search.Result> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_WATCH, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * BiWatch returns a stream of Google search results for the stream of queries.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.grpc.search.Request> biWatch(
        io.grpc.stub.StreamObserver<com.grpc.search.Result> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(METHOD_BI_WATCH, getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class SearchEngineBlockingStub extends io.grpc.stub.AbstractStub<SearchEngineBlockingStub> {
    private SearchEngineBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SearchEngineBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SearchEngineBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SearchEngineBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Search returns a Search Engine result for the query.
     * </pre>
     */
    public com.grpc.search.Result search(com.grpc.search.Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_SEARCH, getCallOptions(), request);
    }

    /**
     * <pre>
     * Watch returns a stream of Google search results for the query.
     * </pre>
     */
    public java.util.Iterator<com.grpc.search.Result> watch(
        com.grpc.search.Request request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_WATCH, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SearchEngineFutureStub extends io.grpc.stub.AbstractStub<SearchEngineFutureStub> {
    private SearchEngineFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SearchEngineFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SearchEngineFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SearchEngineFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Search returns a Search Engine result for the query.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.search.Result> search(
        com.grpc.search.Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SEARCH, getCallOptions()), request);
    }
  }

  private static final int METHODID_SEARCH = 0;
  private static final int METHODID_WATCH = 1;
  private static final int METHODID_BI_WATCH = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SearchEngineImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SearchEngineImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEARCH:
          serviceImpl.search((com.grpc.search.Request) request,
              (io.grpc.stub.StreamObserver<com.grpc.search.Result>) responseObserver);
          break;
        case METHODID_WATCH:
          serviceImpl.watch((com.grpc.search.Request) request,
              (io.grpc.stub.StreamObserver<com.grpc.search.Result>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_BI_WATCH:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.biWatch(
              (io.grpc.stub.StreamObserver<com.grpc.search.Result>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SearchEngineBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SearchEngineBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.grpc.search.SearchProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SearchEngine");
    }
  }

  private static final class SearchEngineFileDescriptorSupplier
      extends SearchEngineBaseDescriptorSupplier {
    SearchEngineFileDescriptorSupplier() {}
  }

  private static final class SearchEngineMethodDescriptorSupplier
      extends SearchEngineBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SearchEngineMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SearchEngineGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SearchEngineFileDescriptorSupplier())
              .addMethod(METHOD_SEARCH)
              .addMethod(METHOD_WATCH)
              .addMethod(METHOD_BI_WATCH)
              .build();
        }
      }
    }
    return result;
  }
}
