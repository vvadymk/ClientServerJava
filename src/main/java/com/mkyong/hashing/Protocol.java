package com.mkyong.hashing;
import java.nio.ByteBuffer;

public class Protocol {

    private static long bPktId=0;
    private static byte bSrc=1;
    private byte bMagic = (byte)Integer.parseInt("13", 16);

    public Protocol(String message, int bUserId, int cType) {


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

    public byte[] messageStructure(String message, int cType, int bUserId) {
        byte[] cTypeB = intToBytes(cType);
        byte[] bUserIdB = intToBytes(bUserId);
        byte[] msg = message.getBytes();
        int wLen = msg.length;
        byte[] messageStructure = new byte[wLen + 8];

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
