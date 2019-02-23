package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBDAO {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String DBName = "taxi";
    static Connection mConnection;
    static Statement mStatement;



    public static Connection getConnection() {
        try {

            if (mConnection == null) {
                // 加载驱动
                Class.forName(JDBC_DRIVER);

                // 设置 URL
                String url = String.format("jdbc:mysql://mysql_service:3306/%s?useSSL=false", DBName);

                // 建立连接
                mConnection = DriverManager.getConnection(url,"root","mysql");
            }
            return mConnection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Statement getStatement() {
        try {
            if (mConnection == null) {
                getConnection();
            }
            Statement statement = mConnection.createStatement();
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
