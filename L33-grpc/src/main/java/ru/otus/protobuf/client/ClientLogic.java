package ru.otus.protobuf.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.protobuf.service.SequenceResponseObserver;

public class ClientLogic {
    private static final Logger logger = LoggerFactory.getLogger(ClientLogic.class);
    private int currentValue = 0;
    private final SequenceResponseObserver responseObserver;

    public ClientLogic(SequenceResponseObserver responseObserver) {
        this.responseObserver = responseObserver;
    }

    public void run() {
        for (int i = 0; i <= 50; i++) {
            int serverValue = responseObserver.getLastValueFromServer();

            currentValue += serverValue + 1;
            logger.info("Current Value: " + currentValue);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("Error while running Client", e);
                Thread.currentThread().interrupt();
            }

        }
    }
}
