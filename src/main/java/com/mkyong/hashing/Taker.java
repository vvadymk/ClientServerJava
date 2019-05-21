package com.mkyong.hashing;

import javax.crypto.Cipher;
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

    public Taker(Protocol protocol, Key key) throws Exception {
        taker(protocol, key);
    }

    private void taker(Protocol protocol, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        bPktId = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes, 2, 10)).getLong();
        wLen = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes, 10, 14)).getInt();
        wCrc16 = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes, 14, 18)).getInt();
        cType = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes, 18, 22)).getInt();
        bUserId = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes, 22, 26)).getInt();
        msgDec = cipher.doFinal(Arrays.copyOfRange(protocol.messageBytes,26, protocol.messageBytes.length-4));
        wCrc16_2 = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes,protocol.messageBytes.length-4,protocol.messageBytes.length)).getInt();
        msg = new String(msgDec, "UTF-8");


        int wCrc16Check = crc16(protocol.messageBytes,0,14);
        int wCrc16Check2 = crc16(protocol.messageBytes,18,18+wLen);

        boolean firstCheck=checkCrc16(wCrc16, wCrc16Check);
        boolean secondCheck= checkCrc16(wCrc16_2, wCrc16Check2);

        if(firstCheck!=true || secondCheck!=true){
           System.out.println("Контрольні суми не співпали");
           return;
        }else{
           System.out.println("Контрольні суми співпали");
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

}
