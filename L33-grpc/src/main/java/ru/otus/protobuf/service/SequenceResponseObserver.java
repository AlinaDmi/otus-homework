package ru.otus.protobuf.service;

import io.grpc.stub.StreamObserver;
import ru.otus.protobuf.SequenceResponse;

import java.util.concurrent.CountDownLatch;

public class SequenceResponseObserver implements StreamObserver<SequenceResponse> {
    private int lastValueFromServer = 0;
    private int currentValue = 0;
    private final CountDownLatch latch;

    public SequenceResponseObserver(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onNext(SequenceResponse value) {
        lastValueFromServer = value.getValue();
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
        latch.countDown();
    }

    @Override
    public void onCompleted() {
        latch.countDown();
    }

    public void runClientLogic() {
        for (int i = 0; i <= 50; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentValue += lastValueFromServer + 1;
            System.out.println("Current Value: " + currentValue);
            lastValueFromServer = 0;
        }
    }
}

