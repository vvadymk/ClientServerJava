package com.mkyong.hashing;

import javax.crypto.Cipher;
import java.nio.ByteBuffer;
import java.security.Key;
import java.util.Arrays;


public class Taker extends CRC16 {

    public void taker(Protocol protocol, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        long bPktId = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes, 2, 10)).getLong();
        int wLen = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes, 10, 14)).getInt();
        int wCrc16 = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes, 14, 18)).getInt();
        int cType = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes, 18, 22)).getInt();
        int bUserId = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes, 22, 26)).getInt();
        byte[] msgDec = cipher.doFinal(Arrays.copyOfRange(protocol.messageBytes,26, protocol.messageBytes.length-4));
        int wCrc16_2 = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes,protocol.messageBytes.length-4,protocol.messageBytes.length)).getInt();
        String msg = new String(msgDec, "UTF-8");

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
}
