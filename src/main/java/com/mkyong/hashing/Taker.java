package com.mkyong.hashing;

public class Taker {
    String res;
    public Taker(byte[] protocol) {
        int wLen = protocol.length-18-4-8;
        byte[] msg = new byte[wLen+8];


        for(int i =0;i<wLen+8;i++){
            msg[i] = protocol[18+i];
        }
         byte[] realMsg =new byte[wLen];
        for(int i=0;i<wLen;i++){
            realMsg[i]=msg[8+i];
        }

        try {
            String res1 = new String(realMsg, "UTF-8");
            System.out.println(res1 );
        }catch (Exception ex){

        }
    }
}
