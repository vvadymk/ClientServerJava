package com.mkyong.hashing;

import org.junit.Assert;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import static junit.framework.TestCase.assertTrue;

public class AppTest {

    @Test
    public void testMessage() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        Key key = keyGen.generateKey();
        Protocol protocol = new Protocol("Test message", 1, 1, key);
        Taker taker = new Taker(protocol, key);
        assertTrue(taker.getMsg().equals("Test message"));
    }
}
