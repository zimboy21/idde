package edu.bbte.idde.zdim1981.dao.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@Profile("jdbc")
public class ConnectionPool {
    @Value("${daoUrl}")
    private String url;
    @Value("${daoDriverClassName}")
    private String driver;
    @Value("${daoUserName:root}")
    private String user;
    @Value("${daoPassword:root}")
    private String password;
    @Value("${daoPoolSize}")
    private Integer pool;

    public DataSource getDataSource() throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driver);
        hikariConfig.setMaximumPoolSize(pool);
        return new HikariDataSource(hikariConfig);
    }
}
