package portalService.test2.user;

import portalService.test2.connection.ConnectionMaker;
import portalService.test2.connection.JejuConnectionMaker;

public class DaoFactory {

    public UserDao userDao() {
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }

    public ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }
}
