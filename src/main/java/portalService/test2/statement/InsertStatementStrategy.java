package portalService.test2.statement;

import portalService.test2.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertStatementStrategy implements StatementStrategy{

    private User user;

    public InsertStatementStrategy(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makeStatement(Connection con) throws SQLException {
        String sql = "insert into userinfo(name,password) values(?,?) ";
        PreparedStatement psmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        psmt.setString(1, user.getName());
        psmt.setString(2, user.getPassword());
        return psmt;
    }
}
