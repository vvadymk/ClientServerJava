package com.mkyong.hashing;

public class AppTest {

//    @Test
//    public void testMessage() throws Exception {
//        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//        keyGen.init(128);
//        Key key = keyGen.generateKey();
//        Protocol protocol = new Protocol("Test message", 1, 1, key);
//        Taker taker = new Taker(protocol, key);
//        assertTrue(taker.getMsg().equals("Test message"));
//    }
//
//    @Test
//    public void testCRC16() throws Exception {
//        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//        keyGen.init(128);
//        Key key = keyGen.generateKey();
//        Protocol protocol = new Protocol("Java", 1, 1, key);
//        Taker taker = new Taker(protocol, key);
//
//        assertTrue(taker.getwCrc16()==protocol.getCrc16() && taker.getwCrc16_2()==protocol.getCrc16_2());
//    }
//
//    @Test
//    public void testUserID() throws Exception{
//        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//        keyGen.init(128);
//        Key key = keyGen.generateKey();
//        Protocol protocol = new Protocol("kma", 10, 1, key);
//        Taker taker = new Taker(protocol, key);
//
//        assertTrue(10==taker.getbUserId());
//    }
//
//    @Test
//    public void testWLen() throws Exception{
//        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//        keyGen.init(128);
//        Key key = keyGen.generateKey();
//        Protocol protocol = new Protocol("kma", 10, 1, key);
//        Taker taker = new Taker(protocol, key);
//
//        assertTrue(protocol.getwLen()==taker.getwLen());
//    }
//
//    @Test
//    public void testThred() throws NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException {
//
//        JSONObject firstItem = new JSONObject();
//        firstItem.put("name", "Grechka");
//        firstItem.put("value", 20);
//        firstItem.put("cost", 20);
//        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
//        list.add(0, firstItem);
//        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//        keyGen.init(128);
//        Key key = keyGen.generateKey();
//        Processor p =new Processor();
//
//        JsonObject first = new JsonObject();
//        first.addProperty("name", "Grechka");
//        first.addProperty("value", 5);
//
//        Protocol pr = new Protocol(first.toString(),1, 1, key);
//        Receiver receiver = new Receiver(pr, key, p);
//        receiver.receive(pr);
//        pr = new Protocol(first.toString(),1,1,key) ;
//        receiver.receive(pr);
//    }
//
//    @Test
//    public void testThread2() throws NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException {
//        JSONObject firstItem = new JSONObject();
//        firstItem.put("name", "grechka");
//        firstItem.put("value", 20);
//        firstItem.put("cost", 20);
//        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
//        list.add(0, firstItem);
//        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//        keyGen.init(128);
//        Key key = keyGen.generateKey();
//        Processor p =new Processor();
//
//        JsonObject first = new JsonObject();
//        first.addProperty("name", "grechka");
//        first.addProperty("value", 100);
//
//        Protocol pr = new Protocol(first.toString(),1, 2, key);
//        Receiver receiver = new Receiver(pr, key, p);
//        receiver.receive(pr);
//    }

}
