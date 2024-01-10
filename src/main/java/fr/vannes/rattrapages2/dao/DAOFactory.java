package fr.vannes.rattrapages2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
    private static String url;
    public static String host = "localhost";
    public static String dbName = "rattrapages2";
    public static String port = "3306";
    public static String username = "root";
    public static String password = "";

    public DAOFactory(String url, String username, String password) {
        DAOFactory.url = url;
        DAOFactory.username = username;
        DAOFactory.password = password;
    }

    public static DAOFactory getInstance() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException var1) {
        }

        DAOFactory instance = new DAOFactory("jdbc:mysql://" + host + ":" + port + "/" + dbName, username, password);
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public UserDAOImpl getUserDAO() throws SQLException {
        return new UserDAOImpl(this);
    }

    public CourseDAOImpl getCourseDAO() throws SQLException {
        return new CourseDAOImpl(this);
    }
}
