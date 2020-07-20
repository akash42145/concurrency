package com.example.threadIntrupt;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ThreadInterrupt {

    private static final Logger logger = Logger.getLogger(ThreadInterrupt.class.getName());

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tT] [%4$-7s] %5$s %n");

        Thread thread = new Thread(() -> {

           // TransferQueue<String> queue = new LinkedTransferQueue<>();
            BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);

            while (!Thread.currentThread().isInterrupted()) {

                try {
                    logger.info(() -> "For 3 seconds the thread "
                            + Thread.currentThread().getName()
                            + " will try to poll an element from queue ...");

                    queue.poll(3000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException ex) {
                    logger.severe(() -> "InterruptedException! The thread "
                            + Thread.currentThread().getName() + " was intrrupted!");
                    Thread.currentThread().interrupt(); // comment this line to see the effect
                }
            }

            logger.info(() -> "The execution was stopped!");
        });

        thread.start();
        Thread.sleep(1500);
        thread.interrupt();
    }

}