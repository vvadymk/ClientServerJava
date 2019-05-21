package com.mkyong.hashing;

import org.junit.Test;
import javax.crypto.KeyGenerator;
import java.security.Key;
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

    @Test
    public void testCRC16() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        Key key = keyGen.generateKey();
        Protocol protocol = new Protocol("Test message", 1, 1, key);
        Taker taker = new Taker(protocol, key);

        assertTrue(taker.getwCrc16()==protocol.getCrc16() && taker.getwCrc16_2()==protocol.getCrc16_2());
    }

    @Test
    public void testUserID() throws Exception{
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        Key key = keyGen.generateKey();
        Protocol protocol = new Protocol("Test message", 10, 1, key);
        Taker taker = new Taker(protocol, key);

        assertTrue(10==taker.getbUserId());
    }
    
}
