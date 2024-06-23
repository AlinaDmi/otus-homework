package ru.otus.protobuf;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import ru.otus.protobuf.service.SequenceResponseObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({"squid:S106", "squid:S2142"})
public class GRPCClient {

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8190)
                .usePlaintext()
                .build();

        SequenceServiceGrpc.SequenceServiceStub asyncStub = SequenceServiceGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(1);

        SequenceResponseObserver responseObserver = new SequenceResponseObserver(latch);
        SequenceRequest request = SequenceRequest.newBuilder()
                .setFirstValue(0)
                .setLastValue(30)
                .build();

        asyncStub.generateSequence(request, responseObserver);

        responseObserver.runClientLogic();

        latch.await(1, TimeUnit.MINUTES);
        channel.shutdown();
    }
}
