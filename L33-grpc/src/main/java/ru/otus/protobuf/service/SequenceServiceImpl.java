package ru.otus.protobuf.service;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.protobuf.GRPCServer;
import ru.otus.protobuf.SequenceRequest;
import ru.otus.protobuf.SequenceResponse;
import ru.otus.protobuf.SequenceServiceGrpc;

public class SequenceServiceImpl extends SequenceServiceGrpc.SequenceServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(SequenceServiceImpl.class);
    @Override
    public void generateSequence(SequenceRequest request, StreamObserver<SequenceResponse> responseObserver) {
        int firstValue = request.getFirstValue();
        int lastValue = request.getLastValue();

        for (int i = firstValue + 1; i <= lastValue; i++) {
            logger.info("Generated value by server: " + i);
            SequenceResponse response = SequenceResponse.newBuilder().setValue(i).build();
            responseObserver.onNext(response);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        responseObserver.onCompleted();
    }
}