package com.mkyong.hashing;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {

        Protocol first = new Protocol("Hellodf", 1, 1);

        Taker taker = new Taker(first.messageBytes);

    }


}
