// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: load.proto

package com.grpc.load;

/**
 * Protobuf type {@code load.Request}
 */
public  final class Request extends
    com.google.protobuf.GeneratedMessageLite<
        Request, Request.Builder> implements
    // @@protoc_insertion_point(message_implements:load.Request)
    RequestOrBuilder {
  private Request() {
    batchSize_ = 0;
    total_ = 0;
  }
  public static final int BATCHSIZE_FIELD_NUMBER = 1;
  private int batchSize_;
  /**
   * <code>optional int32 batchSize = 1;</code>
   */
  public int getBatchSize() {
    return batchSize_;
  }
  /**
   * <code>optional int32 batchSize = 1;</code>
   */
  private void setBatchSize(int value) {
    
    batchSize_ = value;
  }
  /**
   * <code>optional int32 batchSize = 1;</code>
   */
  private void clearBatchSize() {
    
    batchSize_ = 0;
  }

  public static final int TOTAL_FIELD_NUMBER = 2;
  private int total_;
  /**
   * <code>optional int32 total = 2;</code>
   */
  public int getTotal() {
    return total_;
  }
  /**
   * <code>optional int32 total = 2;</code>
   */
  private void setTotal(int value) {
    
    total_ = value;
  }
  /**
   * <code>optional int32 total = 2;</code>
   */
  private void clearTotal() {
    
    total_ = 0;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (batchSize_ != 0) {
      output.writeInt32(1, batchSize_);
    }
    if (total_ != 0) {
      output.writeInt32(2, total_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;

    size = 0;
    if (batchSize_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, batchSize_);
    }
    if (total_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, total_);
    }
    memoizedSerializedSize = size;
    return size;
  }

  public static com.grpc.load.Request parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.grpc.load.Request parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.grpc.load.Request parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static com.grpc.load.Request parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static com.grpc.load.Request parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static com.grpc.load.Request parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static com.grpc.load.Request parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input);
  }
  public static com.grpc.load.Request parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static com.grpc.load.Request parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static com.grpc.load.Request parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.grpc.load.Request prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  /**
   * Protobuf type {@code load.Request}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageLite.Builder<
        com.grpc.load.Request, Builder> implements
      // @@protoc_insertion_point(builder_implements:load.Request)
      com.grpc.load.RequestOrBuilder {
    // Construct using com.grpc.load.Request.newBuilder()
    private Builder() {
      super(DEFAULT_INSTANCE);
    }


    /**
     * <code>optional int32 batchSize = 1;</code>
     */
    public int getBatchSize() {
      return instance.getBatchSize();
    }
    /**
     * <code>optional int32 batchSize = 1;</code>
     */
    public Builder setBatchSize(int value) {
      copyOnWrite();
      instance.setBatchSize(value);
      return this;
    }
    /**
     * <code>optional int32 batchSize = 1;</code>
     */
    public Builder clearBatchSize() {
      copyOnWrite();
      instance.clearBatchSize();
      return this;
    }

    /**
     * <code>optional int32 total = 2;</code>
     */
    public int getTotal() {
      return instance.getTotal();
    }
    /**
     * <code>optional int32 total = 2;</code>
     */
    public Builder setTotal(int value) {
      copyOnWrite();
      instance.setTotal(value);
      return this;
    }
    /**
     * <code>optional int32 total = 2;</code>
     */
    public Builder clearTotal() {
      copyOnWrite();
      instance.clearTotal();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:load.Request)
  }
  protected final Object dynamicMethod(
      com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
      Object arg0, Object arg1) {
    switch (method) {
      case NEW_MUTABLE_INSTANCE: {
        return new com.grpc.load.Request();
      }
      case IS_INITIALIZED: {
        return DEFAULT_INSTANCE;
      }
      case MAKE_IMMUTABLE: {
        return null;
      }
      case NEW_BUILDER: {
        return new Builder();
      }
      case VISIT: {
        Visitor visitor = (Visitor) arg0;
        com.grpc.load.Request other = (com.grpc.load.Request) arg1;
        batchSize_ = visitor.visitInt(batchSize_ != 0, batchSize_,
            other.batchSize_ != 0, other.batchSize_);
        total_ = visitor.visitInt(total_ != 0, total_,
            other.total_ != 0, other.total_);
        if (visitor == com.google.protobuf.GeneratedMessageLite.MergeFromVisitor
            .INSTANCE) {
        }
        return this;
      }
      case MERGE_FROM_STREAM: {
        com.google.protobuf.CodedInputStream input =
            (com.google.protobuf.CodedInputStream) arg0;
        com.google.protobuf.ExtensionRegistryLite extensionRegistry =
            (com.google.protobuf.ExtensionRegistryLite) arg1;
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              default: {
                if (!input.skipField(tag)) {
                  done = true;
                }
                break;
              }
              case 8: {

                batchSize_ = input.readInt32();
                break;
              }
              case 16: {

                total_ = input.readInt32();
                break;
              }
            }
          }
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw new RuntimeException(e.setUnfinishedMessage(this));
        } catch (java.io.IOException e) {
          throw new RuntimeException(
              new com.google.protobuf.InvalidProtocolBufferException(
                  e.getMessage()).setUnfinishedMessage(this));
        } finally {
        }
      }
      case GET_DEFAULT_INSTANCE: {
        return DEFAULT_INSTANCE;
      }
      case GET_PARSER: {
        if (PARSER == null) {    synchronized (com.grpc.load.Request.class) {
            if (PARSER == null) {
              PARSER = new DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            }
          }
        }
        return PARSER;
      }
    }
    throw new UnsupportedOperationException();
  }


  // @@protoc_insertion_point(class_scope:load.Request)
  private static final com.grpc.load.Request DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new Request();
    DEFAULT_INSTANCE.makeImmutable();
  }

  public static com.grpc.load.Request getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static volatile com.google.protobuf.Parser<Request> PARSER;

  public static com.google.protobuf.Parser<Request> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
}

