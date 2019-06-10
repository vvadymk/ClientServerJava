package com.mkyong.hashing;

import java.nio.ByteBuffer;
import java.security.Key;
import java.util.Arrays;

public class Taker extends CRC16 {

    private long bPktId;
    private int wLen;
    private int wCrc16;
    private int cType;
    private int bUserId;
    private byte[] msgDec;
    private int wCrc16_2;
    private String msg;

    public Taker(byte[] bytes, Key key) throws Exception {
        taker(bytes, key);
    }

    private void taker(byte[] bytes, Key key) throws Exception {
//        Cipher cipher = Cipher.getInstance("AES");
//        cipher.init(Cipher.DECRYPT_MODE, key);

        bPktId = ByteBuffer.wrap(Arrays.copyOfRange(bytes, 2, 10)).getLong();
        wLen = ByteBuffer.wrap(Arrays.copyOfRange(bytes, 10, 14)).getInt();
        wCrc16 = ByteBuffer.wrap(Arrays.copyOfRange(bytes, 14, 18)).getInt();
        cType = ByteBuffer.wrap(Arrays.copyOfRange(bytes, 18, 22)).getInt();
        bUserId = ByteBuffer.wrap(Arrays.copyOfRange(bytes, 22, 26)).getInt();
        msgDec = Arrays.copyOfRange(bytes,26, bytes.length-4);
        wCrc16_2 = ByteBuffer.wrap(Arrays.copyOfRange(bytes,bytes.length-4,bytes.length)).getInt();
        msg = new String(msgDec, "UTF-8");

        int wCrc16Check = crc16(bytes,0,14);
        int wCrc16Check2 = crc16(bytes,18,18+wLen);

        boolean firstCheck=checkCrc16(wCrc16, wCrc16Check);
        boolean secondCheck= checkCrc16(wCrc16_2, wCrc16Check2);

        if(firstCheck!=true || secondCheck!=true){
           System.out.println("\n" +
                   "The checksums did not match");
           return;
        }else{
           System.out.println("\n" +
                   "The checksums matched");
        }

        System.out.println("bPktId: " + bPktId);
        System.out.println("wLen: " + wLen);
        System.out.println("cType: " + cType);
        System.out.println("bUserId: " + bUserId);
        System.out.println(msg);
    }

    public boolean checkCrc16(int a, int b){
        if(a==b)
            return true;
        return false;
    }

    public int getbUserId() {
        return bUserId;
    }

    public int getcType() {
        return cType;
    }

    public long getbPktId() {
        return bPktId;
    }

    public int getwLen() {
        return wLen;
    }

    public int getwCrc16() {
        return wCrc16;
    }

    public int getwCrc16_2() {
        return wCrc16_2;
    }

    public String getMsg() {
        return msg;
    }

    public byte[] getMsgDec() {
        return msgDec;
    }
}
