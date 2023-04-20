package portalService.test2.user;

import java.sql.*;

import static portalService.test2.connection.ConnectionConst.*;

public class UserDao {
    public User findById(Long id) throws SQLException {
        Connection con = DriverManager.getConnection(URL_JEJU, USERNAME_JEJU, PASSWORD_JEJU);
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
}
