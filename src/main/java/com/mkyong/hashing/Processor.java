package com.mkyong.hashing;

import org.json.simple.JSONObject;

public class Processor {

    JSONObject d;
    public Processor(JSONObject d){
        this.d = d;
    }
    public boolean process(){

        if(!d.isEmpty()){
            System.out.println("OK");
            return true;
        }
        return false;
    }

}
