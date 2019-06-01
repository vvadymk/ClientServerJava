package com.mkyong.hashing;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class Processor {
    ArrayList<JSONObject> list;
    ArrayList<String> groupOfGoods;
    JSONObject firstItem;
    public Processor(){
        firstItem = new JSONObject();
        firstItem.put("name", "Grechka");
        firstItem.put("value", 20);
        firstItem.put("cost", 20);
        list = new ArrayList<JSONObject>();
        list.add(0, firstItem);
        groupOfGoods = new ArrayList<String>();
    }

    public void process(Taker taker){

      new Thread(()->{
          try {
              Thread.sleep(100);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          if(taker.getcType()==0)
          {
              String a = list.get(0).get("value").toString();
              System.out.println(a);
          }else if(taker.getcType()==1){
              JSONParser jsonParser = new JSONParser();

              try {
                  JSONObject  s = (JSONObject) jsonParser.parse(taker.getMsg());
                  String a = s.get("value").toString();
                  int c = Integer.parseInt(a);
                  int b =Integer.parseInt(list.get(0).get("value").toString());
                  JSONObject obj = new JSONObject();
                  obj = list.get(0);
                  obj.put("value", c+b);
                  list.add(0, obj);
                  System.out.println(list.get(0).toString());
              } catch (ParseException e) {
                  e.printStackTrace();
              }
          }else if(taker.getcType()==2){
              JSONParser jsonParser = new JSONParser();
              try {
                  JSONObject  s = (JSONObject) jsonParser.parse(taker.getMsg());
                  String a = s.get("value").toString();
                  int c = Integer.parseInt(a);
                  int b =Integer.parseInt(list.get(0).get("value").toString());
                  JSONObject obj = new JSONObject();
                  if(b-c>=0) {
                      obj = list.get(0);
                      obj.put("value", b - c);
                      list.add(0, obj);
                      System.out.println(list.get(0).toString());
                  }else{
                      throw new Exception();

                  }
              } catch (ParseException e) {
                  e.printStackTrace();
              } catch (Exception e) {
                  System.out.println("Invalid values");
              }

          }
      }).start();
    }

}
