package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBDAO {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String dataBase = "taxi";
    static String user = "root";
    static String password = "mysql";




    public static Connection getConnection() {
        String url = String.format("jdbc:mysql://mysql_service:3306/%s?useSSL=false", dataBase);
        try {

            // 加载驱动
            Class.forName(JDBC_DRIVER);
            // 建立连接
            Connection con;
            con = DriverManager.getConnection(url, user, password);

            return con;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
