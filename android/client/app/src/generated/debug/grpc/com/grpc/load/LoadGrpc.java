package com.grpc.load;

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
    comments = "Source: load.proto")
public class LoadGrpc {

  private LoadGrpc() {}

  public static final String SERVICE_NAME = "load.Load";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.grpc.load.Request,
      com.grpc.load.Result> METHOD_LOAD =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "load.Load", "Load"),
          io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(com.grpc.load.Request.getDefaultInstance()),
          io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(com.grpc.load.Result.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.grpc.load.Request,
      com.grpc.load.Result> METHOD_LOAD_STREAM =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING,
          generateFullMethodName(
              "load.Load", "LoadStream"),
          io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(com.grpc.load.Request.getDefaultInstance()),
          io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(com.grpc.load.Result.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LoadStub newStub(io.grpc.Channel channel) {
    return new LoadStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LoadBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LoadBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static LoadFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LoadFutureStub(channel);
  }

  /**
   */
  public static abstract class LoadImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Load loads specified number of users.
     * </pre>
     */
    public void load(com.grpc.load.Request request,
        io.grpc.stub.StreamObserver<com.grpc.load.Result> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LOAD, responseObserver);
    }

    /**
     * <pre>
     * LoadStream loads specified numeber of users but in stream.
     * </pre>
     */
    public void loadStream(com.grpc.load.Request request,
        io.grpc.stub.StreamObserver<com.grpc.load.Result> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_LOAD_STREAM, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_LOAD,
            asyncUnaryCall(
              new MethodHandlers<
                com.grpc.load.Request,
                com.grpc.load.Result>(
                  this, METHODID_LOAD)))
          .addMethod(
            METHOD_LOAD_STREAM,
            asyncServerStreamingCall(
              new MethodHandlers<
                com.grpc.load.Request,
                com.grpc.load.Result>(
                  this, METHODID_LOAD_STREAM)))
          .build();
    }
  }

  /**
   */
  public static final class LoadStub extends io.grpc.stub.AbstractStub<LoadStub> {
    private LoadStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LoadStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LoadStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LoadStub(channel, callOptions);
    }

    /**
     * <pre>
     * Load loads specified number of users.
     * </pre>
     */
    public void load(com.grpc.load.Request request,
        io.grpc.stub.StreamObserver<com.grpc.load.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LOAD, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * LoadStream loads specified numeber of users but in stream.
     * </pre>
     */
    public void loadStream(com.grpc.load.Request request,
        io.grpc.stub.StreamObserver<com.grpc.load.Result> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(METHOD_LOAD_STREAM, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LoadBlockingStub extends io.grpc.stub.AbstractStub<LoadBlockingStub> {
    private LoadBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LoadBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LoadBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LoadBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Load loads specified number of users.
     * </pre>
     */
    public com.grpc.load.Result load(com.grpc.load.Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_LOAD, getCallOptions(), request);
    }

    /**
     * <pre>
     * LoadStream loads specified numeber of users but in stream.
     * </pre>
     */
    public java.util.Iterator<com.grpc.load.Result> loadStream(
        com.grpc.load.Request request) {
      return blockingServerStreamingCall(
          getChannel(), METHOD_LOAD_STREAM, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LoadFutureStub extends io.grpc.stub.AbstractStub<LoadFutureStub> {
    private LoadFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LoadFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LoadFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LoadFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Load loads specified number of users.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.grpc.load.Result> load(
        com.grpc.load.Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LOAD, getCallOptions()), request);
    }
  }

  private static final int METHODID_LOAD = 0;
  private static final int METHODID_LOAD_STREAM = 1;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LoadImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(LoadImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOAD:
          serviceImpl.load((com.grpc.load.Request) request,
              (io.grpc.stub.StreamObserver<com.grpc.load.Result>) responseObserver);
          break;
        case METHODID_LOAD_STREAM:
          serviceImpl.loadStream((com.grpc.load.Request) request,
              (io.grpc.stub.StreamObserver<com.grpc.load.Result>) responseObserver);
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
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_LOAD,
        METHOD_LOAD_STREAM);
  }

}
