package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import server.implementation.ChatService;

import java.io.IOException;

public class Main
{

    public static void main(String[] args)
    {

        Server server = ServerBuilder
                .forPort(9090)
                .addService(new ChatService())
                .build();
        try
        {
            server.start();
            server.awaitTermination();
        }
        catch (IOException e)
        {
            System.err.println("ERROR: Could not start the server");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
