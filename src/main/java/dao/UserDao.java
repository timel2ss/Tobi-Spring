package dao;

import domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement psmt = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        psmt.setString(1, user.getId());
        psmt.setString(2, user.getName());
        psmt.setString(3, user.getPassword());

        psmt.executeUpdate();

        psmt.close();
        c.close();
    }

    public User get(String id) throws SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement psmt = c.prepareStatement("select * from users where id = ?");
        psmt.setString(1, id);

        ResultSet rs = psmt.executeQuery();

        User user = null;
        if(rs.next()) {
            user = new User();
            user.setId(rs.getString(1));
            user.setName(rs.getString(2));
            user.setPassword(rs.getString(3));
        }

        if(user == null) {
            throw new EmptyResultDataAccessException(1);
        }

        rs.close();
        psmt.close();
        c.close();

        return user;
    }

    public void deleteAll() throws SQLException {
        StatementStrategy stmt = new DeleteAllStatement();
        jdbcContextWithStatementStrategy(stmt);
    }

    public int getCount() throws SQLException {
        Connection c = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            c = dataSource.getConnection();
            psmt = c.prepareStatement("select count(*) from users");

            rs = psmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if(psmt != null) {
                try {
                    psmt.close();
                } catch (SQLException e) {
                }
            }
            if(c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection c = null;
        PreparedStatement psmt = null;

        try {
            c = dataSource.getConnection();
            psmt = stmt.makePreparedStatement(c);
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if(psmt != null) {
                try {
                    psmt.close();
                } catch (SQLException e) {
                }
            }
            if(c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
