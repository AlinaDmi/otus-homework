package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static boolean isFirstWritingNow = true;

    public static void main(String[] args) {
        Main main = new Main();
        new Thread(() -> main.write(true, "Thread1")).start();
        new Thread(() -> main.write(false, "Thread2")).start();
    }

    private synchronized void write(boolean isFirst, String threadName) {
        int last = 0;
        for (int i = 1; i < 20; i++) {
            try {
                while ((isFirstWritingNow && !isFirst) || (!isFirstWritingNow && isFirst)) {
                    this.wait();
                }

                logger.info("Thread {}: {}", threadName, last >= 10 ? 19 - last : i);
                last = i;
                isFirstWritingNow = !isFirstWritingNow;
                notifyAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}