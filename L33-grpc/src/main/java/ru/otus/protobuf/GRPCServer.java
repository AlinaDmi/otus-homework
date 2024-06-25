package ru.otus.protobuf;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.protobuf.service.SequenceServiceImpl;

import java.io.IOException;

@SuppressWarnings({"squid:S106"})
public class GRPCServer {
    private static final Logger logger = LoggerFactory.getLogger(GRPCServer.class);
    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(8190)
                .addService(new SequenceServiceImpl())
                .build();

        server.start();
        logger.info("Server started on port 8190");
        server.awaitTermination();
    }
}
