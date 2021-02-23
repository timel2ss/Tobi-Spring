package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker {

    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        // N사 DB connection 생성코드
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mariadb://localhost/tobi_spring", "root", "");
    }
}
