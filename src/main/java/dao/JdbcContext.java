package dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
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
