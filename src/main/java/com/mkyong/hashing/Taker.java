package com.mkyong.hashing;

import javax.crypto.Cipher;
import java.nio.ByteBuffer;
import java.security.Key;
import java.util.Arrays;


public class Taker extends CRC16 {

    public void taker(Protocol protocol, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte bMagic = protocol.messageBytes[0];
        byte bSrc = protocol.messageBytes[1];
        long bPktId = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes, 2, 10)).getLong();
        int wLen = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes, 10, 14)).getInt();
        int wCrc16 = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes, 14, 18)).getInt();
        int cType = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes, 18, 22)).getInt();
        int bUserId = ByteBuffer.wrap(Arrays.copyOfRange(protocol.messageBytes, 22, 26)).getInt();
        byte[] msgEnc = Arrays.copyOfRange(protocol.messageBytes,26, protocol.messageBytes.length-12);
        byte[] msgDec = cipher.doFinal(msgEnc);
        String msg = new String(msgDec, "UTF-8");

        int wCrc16Check = crc16(protocol.messageBytes,0,14);
        if(wCrc16==wCrc16Check)
        {
            System.out.println("wCrc16 співпало");

        }else{
            System.out.println("wCrc16 не співпало");
        }

        System.out.println("bPktId: " + bPktId);
        System.out.println("wLen: " + wLen);
        System.out.println("cType: " + cType);
        System.out.println("bUserId: " + bUserId);
        System.out.println(msg);
    }

}
