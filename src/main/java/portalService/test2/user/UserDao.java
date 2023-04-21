package portalService.test2.user;

import portalService.test2.connection.ConnectionMaker;
import portalService.test2.statement.*;

import javax.sql.DataSource;
import java.sql.*;

import static portalService.test2.connection.ConnectionConst.*;

public class UserDao {

    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User findById(Long id) throws SQLException {
        StatementStrategy statementStrategy = con -> {
            String sql = "select id,name,password from userinfo where id=?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setLong(1, id);
            return psmt;
        };
        User user = jdbcContext.jdbcContextForFind(statementStrategy);
        return user;
    }

    public void insert(User user) throws SQLException {
        StatementStrategy statementStrategy = con -> {
            String sql = "insert into userinfo(name,password) values(?,?) ";
            PreparedStatement psmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, user.getName());
            psmt.setString(2, user.getPassword());
            return psmt;
        };
        jdbcContext.jdbcContextForInsert(user,statementStrategy);
    }

    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = con -> {
            String sql = "update userinfo set name=? , password=? where id=? ";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, user.getName());
            psmt.setString(2, user.getPassword());
            psmt.setLong(3,user.getId());
            return psmt;
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = con -> {
            String sql = "delete from userinfo where id=? ";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setLong(1, id);
            return psmt;
        };
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}
