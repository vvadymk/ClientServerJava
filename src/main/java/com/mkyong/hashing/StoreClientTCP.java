package com.mkyong.hashing;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Base64;

public class StoreClientTCP {
    protected Socket clientSocket = null;
    protected String serverText = null;
    protected Processor processor = null;

    public  StoreClientTCP(Socket clientSocket, String serverText, Processor processor) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
        this.processor = processor;
    }

    public void run() {
        try {
            OutputStream output = clientSocket.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message = bufferedReader.readLine();
            byte[] decoded = Base64.getDecoder().decode(message);
            System.out.println("Received a message...");

            String encodedKey = "Gmi2Yeo4ZK3aONigi9PSpDfTJYaaJme7O4DG9AauW0M=";
            byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
            SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

            new Receiver(, secretKey, processor);
            output.write(("OK").getBytes());

            output.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
