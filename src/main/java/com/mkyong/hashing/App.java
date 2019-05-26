package com.mkyong.hashing;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class App
{
    public static void main(String[] args) throws Exception {

        JSONObject firstItem = new JSONObject();
        firstItem.put("name", "Grechka");
        firstItem.put("value", 20);
        firstItem.put("cost", 20);
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list.add(0, firstItem);
        Receiver receiver = new Receiver();
        receiver.receiveMessage(list);

    }

}
