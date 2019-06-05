package com.mkyong.hashing;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Processor {
    ArrayList<JSONObject> list;
    ArrayList<String> groupOfGoods;
    JSONObject firstItem;
    Map<String,Good> goods = new HashMap<String,Good>();
    Good good ;
    public Processor(){
        Good g = new Good("grechka", 10, 10);
        goods.put(g.getName(), g);
    }

    public void process(Taker taker){

      new Thread(()->{
          try {
              Thread.sleep(100);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          JSONParser jsonParser = new JSONParser();
          try {
              JSONObject  s = null;
              try {
                  s = (JSONObject) jsonParser.parse(taker.getMsg());
              } catch (ParseException e) {
                  e.printStackTrace();
              }
              String name=s.get("name").toString();
              String value = s.get("value").toString();
              int valueToInt = Integer.parseInt(value);
              String price = s.get("price").toString();
              double priceToDouble = Double.parseDouble(price);
              good = new Good(name, priceToDouble, valueToInt);
              if(taker.getcType()==0)
              {
                  goods.get(good.getName());
                  System.out.println( goods.get(good.getName()));
              }else if(taker.getcType()==1){
                  goods.get(good.getName()).setValue(goods.get(good.getName()).getValue()+good.getValue());
                  System.out.println(goods.get(good.getName()).getValue());
              }else if(taker.getcType()==2){

                  goods.get(good.getName()).setValue(goods.get(good.getName()).getValue()-good.getValue());
                  System.out.println(goods.get(good.getName()).getValue());
                  }

          }catch (Exception e) {
          }
      }).start();
    }

    }

