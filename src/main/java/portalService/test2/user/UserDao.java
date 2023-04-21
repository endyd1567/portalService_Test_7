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
        String sql = "select id,name,password from userinfo where id=?";
        Object[] params = new Object[]{id};
        User user = jdbcContext.find(sql, params);
        return user;
    }

    public void insert(User user) throws SQLException {
        String sql = "insert into userinfo(name,password) values(?,?)";
        Object[] params = new Object[]{user.getName(),user.getPassword()};
        jdbcContext.insert(user,sql,params);
    }

    public void update(User user) throws SQLException {
        String sql = "update userinfo set name=? , password=? where id=? ";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        jdbcContext.update(sql,params);
    }

    public void delete(Long id) throws SQLException {
        String sql = "delete from userinfo where id=? ";
        Object[] params = new Object[]{id};
        jdbcContext.update(sql,params);
    }

}
