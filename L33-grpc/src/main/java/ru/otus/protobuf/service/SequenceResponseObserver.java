package ru.otus.protobuf.service;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.protobuf.SequenceResponse;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class SequenceResponseObserver implements StreamObserver<SequenceResponse> {
    private static final Logger logger = LoggerFactory.getLogger(SequenceResponseObserver.class);
    private final AtomicInteger lastValueFromServer = new AtomicInteger(0);
    private final CountDownLatch latch;

    public SequenceResponseObserver(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onNext(SequenceResponse value) {
        lastValueFromServer.set(value.getValue());
    }

    @Override
    public void onError(Throwable t) {
        logger.error("Error occurred", t);
        latch.countDown();
    }

    @Override
    public void onCompleted() {
        latch.countDown();
    }

    public int getLastValueFromServer() {
        return lastValueFromServer.getAndSet(0);
    }
}
