package com.mkyong.hashing;

import java.util.ArrayList;

public class App
{
    public static void main(String[] args) throws Exception {
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(0, 10);
        Receiver receiver = new Receiver();
        receiver.receiveMessage(list);

    }

}
