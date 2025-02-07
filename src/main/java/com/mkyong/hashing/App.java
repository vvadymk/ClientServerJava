package com.mkyong.hashing;

import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args) throws Exception {

        JSONObject firstItem = new JSONObject();
        firstItem.put("name", "grechka");
        firstItem.put("value", 20);
        firstItem.put("cost", 20);
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list.add(0, firstItem);
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        Key key = keyGen.generateKey();
        Processor p =new Processor();

        JsonObject first = new JsonObject();
        first.addProperty("name", "grechka");
        first.addProperty("value", 10);
        first.addProperty("price", 10);

        Protocol pr = new Protocol(first.toString(),1, 1, key);
        Receiver receiver = new Receiver(pr.getMessageBytes(), key, p);
    }

}
