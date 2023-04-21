package portalService.test2.user;

import portalService.test2.statement.StatementStrategy;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcContext {

    private final DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User jdbcContextForFind(StatementStrategy statementStrategy) throws SQLException {

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            con = dataSource.getConnection();
            psmt = statementStrategy.makeStatement(con);
            rs = psmt.executeQuery();

            if(rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }

        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                psmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return user;
    }


    public void jdbcContextForInsert(User user, StatementStrategy statementStrategy) throws SQLException {

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = dataSource.getConnection();
            psmt = statementStrategy.makeStatement(con);
            psmt.executeUpdate();

            rs = psmt.getGeneratedKeys();
            rs.next();
            user.setId(rs.getLong(1));
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                psmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
        Connection con = null;
        PreparedStatement psmt = null;

        try {
            con = dataSource.getConnection();
            psmt = statementStrategy.makeStatement(con);
            psmt.executeUpdate();

        }finally {
            try {
                psmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
