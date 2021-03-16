package dao;

import domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {
    @Bean
    public UserDaoJdbc userDao() {
        UserDaoJdbc userDaoJdbc = new UserDaoJdbc();
        userDaoJdbc.setDataSource(dataSource());
        userDaoJdbc.setUserMapper(userMapper());
        return userDaoJdbc;
    }

    @Bean
    public RowMapper<User> userMapper() {
        return new UserMapper();
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.mariadb.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mariadb://localhost/Tobi_Spring");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
}
