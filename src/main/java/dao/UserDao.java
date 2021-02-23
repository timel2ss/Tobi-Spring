package dao;

import domain.User;

import java.sql.*;

public class UserDao {
    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement psmt = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        psmt.setString(1, user.getId());
        psmt.setString(2, user.getName());
        psmt.setString(3, user.getPassword());

        psmt.executeUpdate();

        psmt.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement psmt = c.prepareStatement("select * from users where id = ?");
        psmt.setString(1, id);

        ResultSet rs = psmt.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString(1));
        user.setName(rs.getString(2));
        user.setPassword(rs.getString(3));

        rs.close();
        psmt.close();
        c.close();

        return user;
    }
}
