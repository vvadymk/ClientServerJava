package com.mkyong.hashing;

public class Good {

    private String name;
    private double price;
    private int value;
    public Good(String name, double price, int value){
        this.name=name;
        this.price=price;
        this.value=value;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getValue(){
        return value;
    }
    public void setValue(int value){
        this.value=value;
    }
}
