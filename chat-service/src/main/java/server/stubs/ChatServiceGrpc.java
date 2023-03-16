package server.stubs;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: chat.proto")
public final class ChatServiceGrpc {

  private ChatServiceGrpc() {}

  public static final String SERVICE_NAME = "ChatService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<server.stubs.Chat.Message,
      server.stubs.Chat.Empty> getSendMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendMessage",
      requestType = server.stubs.Chat.Message.class,
      responseType = server.stubs.Chat.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.stubs.Chat.Message,
      server.stubs.Chat.Empty> getSendMessageMethod() {
    io.grpc.MethodDescriptor<server.stubs.Chat.Message, server.stubs.Chat.Empty> getSendMessageMethod;
    if ((getSendMessageMethod = ChatServiceGrpc.getSendMessageMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getSendMessageMethod = ChatServiceGrpc.getSendMessageMethod) == null) {
          ChatServiceGrpc.getSendMessageMethod = getSendMessageMethod = 
              io.grpc.MethodDescriptor.<server.stubs.Chat.Message, server.stubs.Chat.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "ChatService", "sendMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.stubs.Chat.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.stubs.Chat.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("sendMessage"))
                  .build();
          }
        }
     }
     return getSendMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.stubs.Chat.User,
      server.stubs.Chat.Message> getReceiveMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveMessage",
      requestType = server.stubs.Chat.User.class,
      responseType = server.stubs.Chat.Message.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<server.stubs.Chat.User,
      server.stubs.Chat.Message> getReceiveMessageMethod() {
    io.grpc.MethodDescriptor<server.stubs.Chat.User, server.stubs.Chat.Message> getReceiveMessageMethod;
    if ((getReceiveMessageMethod = ChatServiceGrpc.getReceiveMessageMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getReceiveMessageMethod = ChatServiceGrpc.getReceiveMessageMethod) == null) {
          ChatServiceGrpc.getReceiveMessageMethod = getReceiveMessageMethod = 
              io.grpc.MethodDescriptor.<server.stubs.Chat.User, server.stubs.Chat.Message>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "ChatService", "receiveMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.stubs.Chat.User.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.stubs.Chat.Message.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("receiveMessage"))
                  .build();
          }
        }
     }
     return getReceiveMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.stubs.Chat.User,
      server.stubs.Chat.TypingUser> getSubscribeForTypingUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "subscribeForTypingUser",
      requestType = server.stubs.Chat.User.class,
      responseType = server.stubs.Chat.TypingUser.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<server.stubs.Chat.User,
      server.stubs.Chat.TypingUser> getSubscribeForTypingUserMethod() {
    io.grpc.MethodDescriptor<server.stubs.Chat.User, server.stubs.Chat.TypingUser> getSubscribeForTypingUserMethod;
    if ((getSubscribeForTypingUserMethod = ChatServiceGrpc.getSubscribeForTypingUserMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getSubscribeForTypingUserMethod = ChatServiceGrpc.getSubscribeForTypingUserMethod) == null) {
          ChatServiceGrpc.getSubscribeForTypingUserMethod = getSubscribeForTypingUserMethod = 
              io.grpc.MethodDescriptor.<server.stubs.Chat.User, server.stubs.Chat.TypingUser>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "ChatService", "subscribeForTypingUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.stubs.Chat.User.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.stubs.Chat.TypingUser.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("subscribeForTypingUser"))
                  .build();
          }
        }
     }
     return getSubscribeForTypingUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.stubs.Chat.TypingNotification,
      server.stubs.Chat.Empty> getNotifyReceiverIsTypingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "notifyReceiverIsTyping",
      requestType = server.stubs.Chat.TypingNotification.class,
      responseType = server.stubs.Chat.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.stubs.Chat.TypingNotification,
      server.stubs.Chat.Empty> getNotifyReceiverIsTypingMethod() {
    io.grpc.MethodDescriptor<server.stubs.Chat.TypingNotification, server.stubs.Chat.Empty> getNotifyReceiverIsTypingMethod;
    if ((getNotifyReceiverIsTypingMethod = ChatServiceGrpc.getNotifyReceiverIsTypingMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getNotifyReceiverIsTypingMethod = ChatServiceGrpc.getNotifyReceiverIsTypingMethod) == null) {
          ChatServiceGrpc.getNotifyReceiverIsTypingMethod = getNotifyReceiverIsTypingMethod = 
              io.grpc.MethodDescriptor.<server.stubs.Chat.TypingNotification, server.stubs.Chat.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "ChatService", "notifyReceiverIsTyping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.stubs.Chat.TypingNotification.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.stubs.Chat.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("notifyReceiverIsTyping"))
                  .build();
          }
        }
     }
     return getNotifyReceiverIsTypingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.stubs.Chat.User,
      server.stubs.Chat.User> getSubscribeToMessageHasBeenReadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "subscribeToMessageHasBeenRead",
      requestType = server.stubs.Chat.User.class,
      responseType = server.stubs.Chat.User.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<server.stubs.Chat.User,
      server.stubs.Chat.User> getSubscribeToMessageHasBeenReadMethod() {
    io.grpc.MethodDescriptor<server.stubs.Chat.User, server.stubs.Chat.User> getSubscribeToMessageHasBeenReadMethod;
    if ((getSubscribeToMessageHasBeenReadMethod = ChatServiceGrpc.getSubscribeToMessageHasBeenReadMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getSubscribeToMessageHasBeenReadMethod = ChatServiceGrpc.getSubscribeToMessageHasBeenReadMethod) == null) {
          ChatServiceGrpc.getSubscribeToMessageHasBeenReadMethod = getSubscribeToMessageHasBeenReadMethod = 
              io.grpc.MethodDescriptor.<server.stubs.Chat.User, server.stubs.Chat.User>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "ChatService", "subscribeToMessageHasBeenRead"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.stubs.Chat.User.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.stubs.Chat.User.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("subscribeToMessageHasBeenRead"))
                  .build();
          }
        }
     }
     return getSubscribeToMessageHasBeenReadMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.stubs.Chat.ReadNotification,
      server.stubs.Chat.Empty> getNotifyReceiverHasReadMessagesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "notifyReceiverHasReadMessages",
      requestType = server.stubs.Chat.ReadNotification.class,
      responseType = server.stubs.Chat.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.stubs.Chat.ReadNotification,
      server.stubs.Chat.Empty> getNotifyReceiverHasReadMessagesMethod() {
    io.grpc.MethodDescriptor<server.stubs.Chat.ReadNotification, server.stubs.Chat.Empty> getNotifyReceiverHasReadMessagesMethod;
    if ((getNotifyReceiverHasReadMessagesMethod = ChatServiceGrpc.getNotifyReceiverHasReadMessagesMethod) == null) {
      synchronized (ChatServiceGrpc.class) {
        if ((getNotifyReceiverHasReadMessagesMethod = ChatServiceGrpc.getNotifyReceiverHasReadMessagesMethod) == null) {
          ChatServiceGrpc.getNotifyReceiverHasReadMessagesMethod = getNotifyReceiverHasReadMessagesMethod = 
              io.grpc.MethodDescriptor.<server.stubs.Chat.ReadNotification, server.stubs.Chat.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "ChatService", "notifyReceiverHasReadMessages"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.stubs.Chat.ReadNotification.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  server.stubs.Chat.Empty.getDefaultInstance()))
                  .setSchemaDescriptor(new ChatServiceMethodDescriptorSupplier("notifyReceiverHasReadMessages"))
                  .build();
          }
        }
     }
     return getNotifyReceiverHasReadMessagesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChatServiceStub newStub(io.grpc.Channel channel) {
    return new ChatServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChatServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChatServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChatServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChatServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ChatServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendMessage(server.stubs.Chat.Message request,
        io.grpc.stub.StreamObserver<server.stubs.Chat.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMessageMethod(), responseObserver);
    }

    /**
     */
    public void receiveMessage(server.stubs.Chat.User request,
        io.grpc.stub.StreamObserver<server.stubs.Chat.Message> responseObserver) {
      asyncUnimplementedUnaryCall(getReceiveMessageMethod(), responseObserver);
    }

    /**
     */
    public void subscribeForTypingUser(server.stubs.Chat.User request,
        io.grpc.stub.StreamObserver<server.stubs.Chat.TypingUser> responseObserver) {
      asyncUnimplementedUnaryCall(getSubscribeForTypingUserMethod(), responseObserver);
    }

    /**
     */
    public void notifyReceiverIsTyping(server.stubs.Chat.TypingNotification request,
        io.grpc.stub.StreamObserver<server.stubs.Chat.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getNotifyReceiverIsTypingMethod(), responseObserver);
    }

    /**
     */
    public void subscribeToMessageHasBeenRead(server.stubs.Chat.User request,
        io.grpc.stub.StreamObserver<server.stubs.Chat.User> responseObserver) {
      asyncUnimplementedUnaryCall(getSubscribeToMessageHasBeenReadMethod(), responseObserver);
    }

    /**
     */
    public void notifyReceiverHasReadMessages(server.stubs.Chat.ReadNotification request,
        io.grpc.stub.StreamObserver<server.stubs.Chat.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getNotifyReceiverHasReadMessagesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.stubs.Chat.Message,
                server.stubs.Chat.Empty>(
                  this, METHODID_SEND_MESSAGE)))
          .addMethod(
            getReceiveMessageMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                server.stubs.Chat.User,
                server.stubs.Chat.Message>(
                  this, METHODID_RECEIVE_MESSAGE)))
          .addMethod(
            getSubscribeForTypingUserMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                server.stubs.Chat.User,
                server.stubs.Chat.TypingUser>(
                  this, METHODID_SUBSCRIBE_FOR_TYPING_USER)))
          .addMethod(
            getNotifyReceiverIsTypingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.stubs.Chat.TypingNotification,
                server.stubs.Chat.Empty>(
                  this, METHODID_NOTIFY_RECEIVER_IS_TYPING)))
          .addMethod(
            getSubscribeToMessageHasBeenReadMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                server.stubs.Chat.User,
                server.stubs.Chat.User>(
                  this, METHODID_SUBSCRIBE_TO_MESSAGE_HAS_BEEN_READ)))
          .addMethod(
            getNotifyReceiverHasReadMessagesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.stubs.Chat.ReadNotification,
                server.stubs.Chat.Empty>(
                  this, METHODID_NOTIFY_RECEIVER_HAS_READ_MESSAGES)))
          .build();
    }
  }

  /**
   */
  public static final class ChatServiceStub extends io.grpc.stub.AbstractStub<ChatServiceStub> {
    private ChatServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendMessage(server.stubs.Chat.Message request,
        io.grpc.stub.StreamObserver<server.stubs.Chat.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receiveMessage(server.stubs.Chat.User request,
        io.grpc.stub.StreamObserver<server.stubs.Chat.Message> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getReceiveMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void subscribeForTypingUser(server.stubs.Chat.User request,
        io.grpc.stub.StreamObserver<server.stubs.Chat.TypingUser> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSubscribeForTypingUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void notifyReceiverIsTyping(server.stubs.Chat.TypingNotification request,
        io.grpc.stub.StreamObserver<server.stubs.Chat.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNotifyReceiverIsTypingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void subscribeToMessageHasBeenRead(server.stubs.Chat.User request,
        io.grpc.stub.StreamObserver<server.stubs.Chat.User> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSubscribeToMessageHasBeenReadMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void notifyReceiverHasReadMessages(server.stubs.Chat.ReadNotification request,
        io.grpc.stub.StreamObserver<server.stubs.Chat.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getNotifyReceiverHasReadMessagesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ChatServiceBlockingStub extends io.grpc.stub.AbstractStub<ChatServiceBlockingStub> {
    private ChatServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public server.stubs.Chat.Empty sendMessage(server.stubs.Chat.Message request) {
      return blockingUnaryCall(
          getChannel(), getSendMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<server.stubs.Chat.Message> receiveMessage(
        server.stubs.Chat.User request) {
      return blockingServerStreamingCall(
          getChannel(), getReceiveMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<server.stubs.Chat.TypingUser> subscribeForTypingUser(
        server.stubs.Chat.User request) {
      return blockingServerStreamingCall(
          getChannel(), getSubscribeForTypingUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.stubs.Chat.Empty notifyReceiverIsTyping(server.stubs.Chat.TypingNotification request) {
      return blockingUnaryCall(
          getChannel(), getNotifyReceiverIsTypingMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<server.stubs.Chat.User> subscribeToMessageHasBeenRead(
        server.stubs.Chat.User request) {
      return blockingServerStreamingCall(
          getChannel(), getSubscribeToMessageHasBeenReadMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.stubs.Chat.Empty notifyReceiverHasReadMessages(server.stubs.Chat.ReadNotification request) {
      return blockingUnaryCall(
          getChannel(), getNotifyReceiverHasReadMessagesMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ChatServiceFutureStub extends io.grpc.stub.AbstractStub<ChatServiceFutureStub> {
    private ChatServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.stubs.Chat.Empty> sendMessage(
        server.stubs.Chat.Message request) {
      return futureUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.stubs.Chat.Empty> notifyReceiverIsTyping(
        server.stubs.Chat.TypingNotification request) {
      return futureUnaryCall(
          getChannel().newCall(getNotifyReceiverIsTypingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.stubs.Chat.Empty> notifyReceiverHasReadMessages(
        server.stubs.Chat.ReadNotification request) {
      return futureUnaryCall(
          getChannel().newCall(getNotifyReceiverHasReadMessagesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_MESSAGE = 0;
  private static final int METHODID_RECEIVE_MESSAGE = 1;
  private static final int METHODID_SUBSCRIBE_FOR_TYPING_USER = 2;
  private static final int METHODID_NOTIFY_RECEIVER_IS_TYPING = 3;
  private static final int METHODID_SUBSCRIBE_TO_MESSAGE_HAS_BEEN_READ = 4;
  private static final int METHODID_NOTIFY_RECEIVER_HAS_READ_MESSAGES = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ChatServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ChatServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_MESSAGE:
          serviceImpl.sendMessage((server.stubs.Chat.Message) request,
              (io.grpc.stub.StreamObserver<server.stubs.Chat.Empty>) responseObserver);
          break;
        case METHODID_RECEIVE_MESSAGE:
          serviceImpl.receiveMessage((server.stubs.Chat.User) request,
              (io.grpc.stub.StreamObserver<server.stubs.Chat.Message>) responseObserver);
          break;
        case METHODID_SUBSCRIBE_FOR_TYPING_USER:
          serviceImpl.subscribeForTypingUser((server.stubs.Chat.User) request,
              (io.grpc.stub.StreamObserver<server.stubs.Chat.TypingUser>) responseObserver);
          break;
        case METHODID_NOTIFY_RECEIVER_IS_TYPING:
          serviceImpl.notifyReceiverIsTyping((server.stubs.Chat.TypingNotification) request,
              (io.grpc.stub.StreamObserver<server.stubs.Chat.Empty>) responseObserver);
          break;
        case METHODID_SUBSCRIBE_TO_MESSAGE_HAS_BEEN_READ:
          serviceImpl.subscribeToMessageHasBeenRead((server.stubs.Chat.User) request,
              (io.grpc.stub.StreamObserver<server.stubs.Chat.User>) responseObserver);
          break;
        case METHODID_NOTIFY_RECEIVER_HAS_READ_MESSAGES:
          serviceImpl.notifyReceiverHasReadMessages((server.stubs.Chat.ReadNotification) request,
              (io.grpc.stub.StreamObserver<server.stubs.Chat.Empty>) responseObserver);
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

  private static abstract class ChatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ChatServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return server.stubs.Chat.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ChatService");
    }
  }

  private static final class ChatServiceFileDescriptorSupplier
      extends ChatServiceBaseDescriptorSupplier {
    ChatServiceFileDescriptorSupplier() {}
  }

  private static final class ChatServiceMethodDescriptorSupplier
      extends ChatServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ChatServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ChatServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ChatServiceFileDescriptorSupplier())
              .addMethod(getSendMessageMethod())
              .addMethod(getReceiveMessageMethod())
              .addMethod(getSubscribeForTypingUserMethod())
              .addMethod(getNotifyReceiverIsTypingMethod())
              .addMethod(getSubscribeToMessageHasBeenReadMethod())
              .addMethod(getNotifyReceiverHasReadMessagesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
