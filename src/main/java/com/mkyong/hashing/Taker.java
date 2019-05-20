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
        byte[] msgEnc = Arrays.copyOfRange(protocol.messageBytes,26, protocol.messageBytes.length-4);
        byte[] msgDec = cipher.doFinal(msgEnc);
        int wCrc16w = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes,protocol.messageBytes.length-4,protocol.messageBytes.length)).getInt();
        String msg = new String(msgDec, "UTF-8");

        int wCrc16Check = crc16(protocol.messageBytes,0,14);
        int wCrc16Check2 = crc16(protocol.messageBytes,18,18+wLen);
        if(wCrc16==wCrc16Check)
        {
            System.out.println("00-13 співпало");
            if(wCrc16Check2==wCrc16w){
                System.out.println("18+wLen-1 співпало");
            }else{
                System.out.println("18+wLen-1 не співпало");
        }

        }else{
            System.out.println("00-13 співпало");
        }

        System.out.println("bPktId: " + bPktId);
        System.out.println("wLen: " + wLen);
        System.out.println("cType: " + cType);
        System.out.println("bUserId: " + bUserId);
        System.out.println(msg);
    }

}
