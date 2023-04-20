package portalService.test2.user;

import java.sql.*;

import static portalService.test2.connection.ConnectionConst.*;

public abstract class UserDao {

    public abstract Connection getConnection() throws SQLException;

    public User findById(Long id) throws SQLException {
        Connection con = getConnection();
        String sql = "select id,name,password from userinfo where id=?";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setLong(1, id);
        ResultSet rs = psmt.executeQuery();

        rs.next();

        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        rs.close();
        psmt.close();
        con.close();

        return user;
    }

    public void insert(User user) throws SQLException {
        Connection con = getConnection();
        String sql = "insert into userinfo(name,password) values(?,?) ";
        PreparedStatement psmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        psmt.setString(1, user.getName());
        psmt.setString(2, user.getPassword());
        psmt.executeUpdate();

        ResultSet rs = psmt.getGeneratedKeys();
        rs.next();
        user.setId(rs.getLong(1));

        rs.close();
        psmt.close();
        con.close();

    }
}
