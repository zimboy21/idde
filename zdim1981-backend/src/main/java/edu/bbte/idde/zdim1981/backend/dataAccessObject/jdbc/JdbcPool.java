package edu.bbte.idde.zdim1981.backend.dataaccessobject.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.bbte.idde.zdim1981.backend.config.Config;
import edu.bbte.idde.zdim1981.backend.config.ConfigFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcPool {
    private static HikariDataSource hoards;
    private static Config config = ConfigFactory.getConfig();

    static {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getDaoUrl());
        hikariConfig.setUsername(config.getDaoUserName());
        hikariConfig.setPassword(config.getDaoPassword());
        hikariConfig.setDriverClassName(config.getDaoDriverClassName());
        hikariConfig.setMaximumPoolSize(config.getDaoPoolSize());

        hoards = new HikariDataSource(hikariConfig);
    }

    public static Connection getConnection() throws SQLException {
        return hoards.getConnection();
    }
}
