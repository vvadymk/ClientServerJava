package com.mkyong.hashing;

import java.security.Key;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Receiver extends Thread {
        private Key key;
        private Processor processor;
    public Receiver(byte[] bytes, Key key, Processor pr){
        processor = pr;
        this.key=key;
        this.receive(bytes);
    }

    public void receive(byte[] bytes){

        final BlockingQueue<Taker> queue = new ArrayBlockingQueue<Taker>(2);
        new Thread(()->{
            try {
                Taker taker = new Taker(bytes, key);
                queue.put(taker);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                processor.process(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
