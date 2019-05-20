package com.mkyong.hashing;
import javax.crypto.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Protocol extends CRC16{

    public static long bPktId=0;
    public static byte bSrc=1;
    public byte bMagic = (byte)Integer.parseInt("13", 16);
    public byte[] messageBytes;

    public Protocol(String message, int bUserId, int cType, Key key) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        byte[] messageStructure = messageStructure(message, cType, bUserId, key);
        int wLen = messageStructure.length;
        messageBytes = new byte[18+wLen+4+8];

        messageBytes[0] = bMagic;
        messageBytes[1]=bSrc;

        byte[] bPktIdB;
        bPktIdB = longToBytes(bPktId);
        messageBytes[2]=bPktIdB[0];
        messageBytes[3]=bPktIdB[1];
        messageBytes[4]=bPktIdB[2];
        messageBytes[5]=bPktIdB[3];
        messageBytes[6]=bPktIdB[4];
        messageBytes[7]=bPktIdB[5];
        messageBytes[8]=bPktIdB[6];
        messageBytes[9]=bPktIdB[7];

        byte[] wLenB = intToBytes(wLen);


        messageBytes[10]=wLenB[0];
        messageBytes[11]=wLenB[1];
        messageBytes[12]=wLenB[2];
        messageBytes[13]=wLenB[3];

        byte[] crc16B = intToBytes(crc16(messageBytes,0,14));
        messageBytes[14] = crc16B[0];
        messageBytes[15]=crc16B[1];
        messageBytes[16]=crc16B[2];
        messageBytes[17]=crc16B[3];

        for(int i=0;i<wLen;i++){
            messageBytes[18+i]=messageStructure[i];

        }

        byte[] crc16B2 = intToBytes(crc16(messageBytes,18, 18+wLen));

        messageBytes[18+wLen]=crc16B2[0];
        messageBytes[18+wLen+1]=crc16B2[1];
        messageBytes[18+wLen+2]=crc16B2[2];
        messageBytes[18+wLen+3]=crc16B2[3];

        bPktId++;

    }

    public byte[] intToBytes( final int i ) {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(i);
        return bb.array();
    }

    public byte[] longToBytes(final long i ) {
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.putLong(i);
        return bb.array();
    }

    public byte[] messageStructure(String message, int cType, int bUserId, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] cTypeB = intToBytes(cType);
        byte[] bUserIdB = intToBytes(bUserId);
        byte[] msg = message.getBytes(StandardCharsets.UTF_8);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        msg = cipher.doFinal(msg);

        int wLen = msg.length;
        byte[] messageStructure = new byte[wLen+8];

        messageStructure[0] = cTypeB[0];
        messageStructure[1] = cTypeB[1];
        messageStructure[2] = cTypeB[2];
        messageStructure[3] = cTypeB[3];
        messageStructure[4] = bUserIdB[0];
        messageStructure[5] = bUserIdB[1];
        messageStructure[6] = bUserIdB[2];
        messageStructure[7] = bUserIdB[3];

        for (int i = 0; i < wLen; i++) {
            messageStructure[8+i] = msg[i];
        }
        return messageStructure;
    }
}
