package ru.ezhov.template.core;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class H2Source implements Source<DataSource> {
    public DataSource get() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:tcp://localhost/~/template");
        config.setUsername("sa");
        config.setPassword("sa");

        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }
}
