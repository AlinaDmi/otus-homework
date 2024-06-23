package ru.otus.protobuf;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import ru.otus.protobuf.service.SequenceServiceImpl;

import java.io.IOException;

@SuppressWarnings({"squid:S106"})
public class GRPCServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8190)
                .addService(new SequenceServiceImpl())
                .build();

        server.start();
        System.out.println("Server started on port 8190");
        server.awaitTermination();
    }
}
