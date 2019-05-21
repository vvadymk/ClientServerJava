package com.mkyong.hashing;

import javax.crypto.*;
import java.security.Key;

public class App 
{
    public static void main(String[] args) throws Exception {

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        Key key = keyGen.generateKey();

        Protocol first = new Protocol("Hello world", 1, 1, key);

        Taker taker = new Taker(first, key);

    }

}
