package com.navi.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JPAConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://ec2-52-70-15-120.compute-1.amazonaws.com:5432/d2f3ttdgsh077h?sslmode=require&user=ytywchmsshtsma&password=0207a5bfc68c7dc244a7fe2ae3d16120fa8c9b5898ee946b135af8ae89ba779c");
        dataSourceBuilder.username("ytywchmsshtsma");
        dataSourceBuilder.password("0207a5bfc68c7dc244a7fe2ae3d16120fa8c9b5898ee946b135af8ae89ba779c");

        return dataSourceBuilder.build();
    }

}
