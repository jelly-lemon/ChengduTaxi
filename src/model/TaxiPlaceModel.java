package model;

import interfaces.ITaxiPlaceServlet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.DBDAO;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaxiPlaceModel {
    private ITaxiPlaceServlet iTaxiPlaceServlet;

    public TaxiPlaceModel(ITaxiPlaceServlet iTaxiPlaceServlet) {
        this.iTaxiPlaceServlet = iTaxiPlaceServlet;
    }


    public void getAllPoint(String sql) {
        if (sql == null) {
            sql = String.format("SELECT * FROM taxi limit 1,100;");
        }
        Connection con = DBDAO.getConnection();
        try {
            Statement statement = con.createStatement();
            //String sql = String.format("SELECT * FROM taxi limit 1,100;");
            ResultSet resultSet = statement.executeQuery(sql);

            JSONArray jsonArray = new JSONArray();
            while (resultSet.next()) {
                int TID = resultSet.getInt("TID");
                double Lon = resultSet.getDouble("Lon");
                double Lat = resultSet.getDouble("Lat");

                // 将时间转化为字符串
                java.sql.Time time = resultSet.getTime("Time");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
                String strTime = simpleDateFormat.format(time);


                JSONObject jsonObject = new JSONObject();
                jsonObject.put("TID", TID);
                jsonObject.put("Lon", Lon);
                jsonObject.put("Lat", Lat);
                jsonObject.put("Time", strTime);

                jsonArray.put(jsonObject);
            }

            System.out.println(jsonArray.toString());
            // 显示结果
            iTaxiPlaceServlet.showResult(jsonArray.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    public void getAllTID() {
        Connection con = DBDAO.getConnection();
        try {
            Statement statement = con.createStatement();
            String sql = String.format("SELECT DISTINCT TID FROM taxi limit 1,100;");
            ResultSet resultSet = statement.executeQuery(sql);

            ArrayList<Integer> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getInt("TID"));
            }

            // 查询完毕，关闭连接
            statement.close();
            con.close();

            iTaxiPlaceServlet.showAllTID(list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getSelectedPoint(String TID, String startTime, String endTime) {

        String sql;
        if (TID.equals("all")) {
            sql = String.format("SELECT * FROM taxi WHERE Time > '%s' && Time < '%s' LIMIT 1,100;", startTime, endTime);
        } else {
            sql = String.format("SELECT * FROM taxi WHERE TID = %s AND Time > '%s' && Time < '%s' LIMIT 1,100;", TID, startTime, endTime);
        }

        getAllPoint(sql);
    }

}
