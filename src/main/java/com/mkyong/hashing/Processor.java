package com.mkyong.hashing;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.*;

public class Processor {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/sklad";
    private static final String user = "root";
    private static final String password = "";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public Processor() {
    }

    public void process(Taker taker) {

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JSONParser jsonParser = new JSONParser();
            try {
                JSONObject s = null;
                try {
                    s = (JSONObject) jsonParser.parse(taker.getMsg());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String name = s.get("name").toString();
                String value = s.get("value").toString();
                int valueToInt = Integer.parseInt(value);
                String price = s.get("price").toString();
                double priceToDouble = Double.parseDouble(price);
                if (taker.getcType() == 0) {
                    String query = "select * from storage";

                    try {

                        con = DriverManager.getConnection(url, user, password);


                        stmt = con.createStatement();

                        // executing SELECT query
                        rs = stmt.executeQuery(query);

                        while (rs.next()) {
                            int id = rs.getInt(1);
                            String name2 = rs.getString(2);
                            System.out.printf("id: %d, name: %s", id, name2);
                        }

                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    } finally {
                        //close connection ,stmt and resultset here
                        try {
                            con.close();
                        } catch (SQLException se) { /*can't do anything */ }
                        try {
                            stmt.close();
                        } catch (SQLException se) { /*can't do anything */ }
                        try {
                            rs.close();
                        } catch (SQLException se) { /*can't do anything */ }
                    }

                } else if (taker.getcType() == 1) {

                    try {
                        con = DriverManager.getConnection(url, user, password);
                        PreparedStatement pstmt = null;
                        pstmt = con.prepareStatement("select * from storage where name=?");
                        pstmt.setString(1, name);
                        rs = pstmt.executeQuery();
                        int v = 0;
                        while (rs.next()) {
                            v = rs.getInt("value");
                        }
                        System.out.println(v);
                        v += valueToInt;
                        PreparedStatement pstmt2 = null;
                        pstmt2 = con.prepareStatement("update storage set value=? where name=?");
                        pstmt2.setInt(1, v);
                        pstmt2.setString(2, name);
                        pstmt2.executeUpdate();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    } finally {
                        //close connection ,stmt and resultset here
                        try {
                            con.close();
                        } catch (SQLException se) { /*can't do anything */ }
                        try {
                            stmt.close();
                        } catch (SQLException se) { /*can't do anything */ }
                        try {
                            rs.close();
                        } catch (SQLException se) { /*can't do anything */ }
                    }

                } else if (taker.getcType() == 2) {

                    try {
                        con = DriverManager.getConnection(url, user, password);
                        PreparedStatement pstmt = null;
                        pstmt = con.prepareStatement("select * from storage where name=?");
                        pstmt.setString(1, name);
                        rs = pstmt.executeQuery();
                        int v = 0;
                        while (rs.next()) {
                            v = rs.getInt("value");
                        }
                        System.out.println(v);
                        v -= valueToInt;
                        PreparedStatement pstmt2 = null;
                        pstmt2 = con.prepareStatement("update storage set value=? where name=?");
                        pstmt2.setInt(1, v);
                        pstmt2.setString(2, name);
                        pstmt2.executeUpdate();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    } finally {
                        //close connection ,stmt and resultset here
                        try {
                            con.close();
                        } catch (SQLException se) { /*can't do anything */ }
                        try {
                            stmt.close();
                        } catch (SQLException se) { /*can't do anything */ }
                        try {
                            rs.close();
                        } catch (SQLException se) { /*can't do anything */ }
                    }

                } else if (taker.getcType() == 3) {
                    try {
                        con = DriverManager.getConnection(url, user, password);
                        PreparedStatement pstmt = null;
                        pstmt = con.prepareStatement("delete from storage where name=?");
                        pstmt.setString(1, name);
                        pstmt.execute();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    } finally {
                        //close connection ,stmt and resultset here
                        try {
                            con.close();
                        } catch (SQLException se) { /*can't do anything */ }
                        try {
                            stmt.close();
                        } catch (SQLException se) { /*can't do anything */ }
                        try {
                            rs.close();
                        } catch (SQLException se) { /*can't do anything */ }
                    }

                } else if (taker.getcType() == 4) {
                    try {
                        con = DriverManager.getConnection(url, user, password);
                        PreparedStatement pstmt = null;
                        pstmt = con.prepareStatement("insert into storage (name, price, value) values(?, ?, ?)");
                        pstmt.setString(1, name);
                        pstmt.setInt(2, (int) priceToDouble);
                        pstmt.setInt(3, valueToInt);
                        pstmt.execute();
                    } catch (SQLException sqlEx) {
                        sqlEx.printStackTrace();
                    } finally {
                        //close connection ,stmt and resultset here
                        try {
                            con.close();
                        } catch (SQLException se) { /*can't do anything */ }
                        try {
                            stmt.close();
                        } catch (SQLException se) { /*can't do anything */ }
                        try {
                            rs.close();
                        } catch (SQLException se) { /*can't do anything */ }
                    }
                }

            } catch (Exception e) {
            }
        }).start();
    }

}


