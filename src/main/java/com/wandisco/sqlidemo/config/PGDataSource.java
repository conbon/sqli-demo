package com.wandisco.sqlidemo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class PGDataSource {

    private javax.sql.DataSource ds;
//    private static HikariConfig config = new HikariConfig();
//    private static HikariDataSource ds;
//
//    @Autowired
//    private static javax.sql.DataSource dataSource;
//
//    static {
////        config.setJdbcUrl();
////        config.setUsername(flywayProperties.getUser());
////        config.setPassword(flywayProperties.getPassword());
//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//        ds = new HikariDataSource(config);
//    }

    @Autowired
    public PGDataSource(javax.sql.DataSource ds) {
        this.ds = ds;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
