package portalService.test2.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import portalService.test2.connection.ConnectionMaker;
import portalService.test2.connection.JejuConnectionMaker;

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao(connectionMaker());
        return userDao;
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }
}
