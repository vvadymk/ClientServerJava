package com.mkyong.hashing;

import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Receiver extends Thread {


        Key key;
        Protocol protocol;
        ArrayList list;
        int cType;
        JSONObject decrypted;
    public void receiveMessage(ArrayList<JSONObject> list) throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        key = keyGen.generateKey();
        JsonObject first = new JsonObject();
        first.addProperty("name", "Grechka");
        first.addProperty("value", 10);

        this.list = list;

        final BlockingQueue<JSONObject> queue = new ArrayBlockingQueue<JSONObject>(2);

        //create package
        protocol = new Protocol(first.toString(), 1, 0, key);


        new Thread() {
            public void run() {
                try {
                    Taker taker = new Taker(protocol, key);
                    cType = taker.getcType();
                    JSONParser jsonParser = new JSONParser();
                    JSONObject jsonObject = (JSONObject) jsonParser.parse(taker.getMsg().toString());
                    queue.put(jsonObject);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


        new Thread() {
            public void run() {
                try {
                    JSONParser jsonParser = new JSONParser();
                    decrypted = (JSONObject) jsonParser.parse(queue.take().toString());
                    Processor p = new Processor(decrypted);
                    if (p.process()) {
                        if (cType == 1) {
                            String a = decrypted.get("value").toString();
                            int c = Integer.parseInt(a);
                            int b =Integer.parseInt(list.get(0).get("value").toString());
                            JSONObject obj = new JSONObject();
                            obj = list.get(0);
                            obj.put("value", c+b);
                            list.add(0, obj);
                           System.out.println(list.get(0).toString());
                        } else if (cType == 0) {
                            String a = list.get(0).get("value").toString();
                            System.out.println(a);
                        } else if (cType == 2) {
                            String a = decrypted.get("value").toString();
                            int c = Integer.parseInt(a);
                            int b =Integer.parseInt(list.get(0).get("value").toString());
                            JSONObject obj = new JSONObject();
                            obj = list.get(0);
                            obj.put("value", b-c);
                            list.add(0, obj);
                            System.out.println(list.get(0).toString());
                        }
                    } else {

                    }
                } catch (Exception e) {

                }
            }
        }.start();

    }
}
