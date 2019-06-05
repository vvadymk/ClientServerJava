package com.mkyong.hashing;

import java.net.ServerSocket;

public class StoreServerTCP extends Thread {

    int port = 6666;
    Processor processor;
    ServerSocket serverSocket;

    public StoreServerTCP(Processor processor) {
        this.processor = processor;
    }

    public void run(){
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (Exception e) {

        }
    }
}
