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
    value = "by gRPC proto compiler (version 1.0.1)",
    comments = "Source: search.proto")
public class GoogleGrpc {

  private GoogleGrpc() {}

  public static final String SERVICE_NAME = "search.Google";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.grpc.search.Request,
      com.grpc.search.Result> METHOD_SEARCH =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "search.Google", "Search"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.grpc.search.Request.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.grpc.search.Result.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.grpc.search.Request,
      com.grpc.search.Result> METHOD_WATCH =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "search.Google", "Watch"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.grpc.search.Request.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.grpc.search.Result.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.grpc.search.Request,
      com.grpc.search.Result> METHOD_BI_WATCH =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING,
          generateFullMethodName(
              "search.Google", "BiWatch"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.grpc.search.Request.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.grpc.search.Result.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GoogleStub newStub(io.grpc.Channel channel) {
    return new GoogleStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GoogleBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GoogleBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static GoogleFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GoogleFutureStub(channel);
  }

  /**
   */
  public static abstract class GoogleImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Search returns a Google search result for the query.
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

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
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
  public static final class GoogleStub extends io.grpc.stub.AbstractStub<GoogleStub> {
    private GoogleStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GoogleStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GoogleStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GoogleStub(channel, callOptions);
    }

    /**
     * <pre>
     * Search returns a Google search result for the query.
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
  public static final class GoogleBlockingStub extends io.grpc.stub.AbstractStub<GoogleBlockingStub> {
    private GoogleBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GoogleBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GoogleBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GoogleBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Search returns a Google search result for the query.
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
  public static final class GoogleFutureStub extends io.grpc.stub.AbstractStub<GoogleFutureStub> {
    private GoogleFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GoogleFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GoogleFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GoogleFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Search returns a Google search result for the query.
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

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GoogleImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(GoogleImplBase serviceImpl, int methodId) {
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

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_SEARCH,
        METHOD_WATCH,
        METHOD_BI_WATCH);
  }

}
