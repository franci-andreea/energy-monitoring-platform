package server.implementation;


import io.grpc.stub.StreamObserver;
import server.stubs.Chat;
import server.stubs.ChatServiceGrpc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ChatService extends ChatServiceGrpc.ChatServiceImplBase
{
    private static final Chat.Empty EMPTY_MESSAGE = Chat.Empty.newBuilder().build();
    private final List<Chat.Message> allSentMessages = new ArrayList<>();
    private final List<Chat.ReadNotification> readNotifications = new ArrayList<>();
    private final List<Chat.TypingNotification> isTypingNotifications = new ArrayList<>();

    @Override
    public void sendMessage(Chat.Message request, StreamObserver<Chat.Empty> responseObserver)
    {
        allSentMessages.add(request);
        responseObserver.onNext(EMPTY_MESSAGE);

        responseObserver.onCompleted();
    }

    @Override
    public void receiveMessage(Chat.User request, StreamObserver<Chat.Message> responseObserver)
    {
        List<Chat.Message> messagesToSend = allSentMessages.stream()
                .filter((message)-> message.getReceiver().equals(request.getUsername()))
                .collect(Collectors.toList());

        messagesToSend.forEach((message) -> {
            allSentMessages.remove(message);
            responseObserver.onNext(message);
        });

        responseObserver.onCompleted();
    }

    @Override
    public void subscribeForTypingUser(Chat.User request, StreamObserver<Chat.TypingUser> responseObserver)
    {
        List<Chat.TypingNotification> isTypingNotificationsToSend = isTypingNotifications.stream()
                        .filter((isTypingNotification) -> isTypingNotification.getReceiver().equals(request.getUsername()))
                        .collect(Collectors.toList());

        isTypingNotificationsToSend.forEach((isTypingNotification) -> {
            isTypingNotifications.remove(isTypingNotification);
            responseObserver.onNext(isTypingNotification.getSender());
        });

        responseObserver.onCompleted();
    }

    @Override
    public void notifyReceiverIsTyping(Chat.TypingNotification request, StreamObserver<Chat.Empty> responseObserver)
    {
        isTypingNotifications.add(request);
        responseObserver.onNext(EMPTY_MESSAGE);

        responseObserver.onCompleted();
    }

    @Override
    public void subscribeToMessageHasBeenRead(Chat.User request, StreamObserver<Chat.User> responseObserver)
    {
        List<Chat.ReadNotification> readNotificationsToSend = readNotifications.stream()
                .filter((readNotification) -> readNotification.getReceiver().equals(request.getUsername()))
                .collect(Collectors.toList());

        readNotificationsToSend.forEach((message) -> {
            readNotifications.remove(message);
            responseObserver.onNext(Chat.User.newBuilder().setUsername(message.getSender()).build());
        });

        responseObserver.onCompleted();
    }

    @Override
    public void notifyReceiverHasReadMessages(Chat.ReadNotification request, StreamObserver<Chat.Empty> responseObserver)
    {
        readNotifications.add(request);

        responseObserver.onNext(EMPTY_MESSAGE);
        responseObserver.onCompleted();
    }
}
