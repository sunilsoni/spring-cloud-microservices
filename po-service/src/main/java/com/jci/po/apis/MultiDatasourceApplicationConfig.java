package com.jci.po.apis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class MultiDatasourceApplicationConfig {


    /*@Bean(name = "jdbcPostgresqlTemplate")
    public JdbcTemplate jdbcPostgresqlTemplate(@Qualifier(value = "postgresqlDataSource") DataSource postgresqlDataSource) {
    	System.out.println("### Starting Ending MultiDatasourceApplicationConfig.jdbcPostgresqlTemplate ###");
        return new JdbcTemplate(postgresqlDataSource);
    }*/
    
    @Bean(name = "jdbcOpenedgeTemplate")
    public JdbcTemplate jdbcOpenedgeTemplate(@Qualifier(value = "openedgeDataSource") DataSource openedgeDataSource) {
    	System.out.println("### Starting Ending MultiDatasourceApplicationConfig.jdbcOpenedgeTemplate ###");
        return new JdbcTemplate(openedgeDataSource);
    }

   /* @Bean(name = "postgresqlDataSource")
    @ConfigurationProperties(prefix="datasource.postgresql")
    public DataSource postgresqlDataSource() {
    	System.out.println("### Starting Ending MultiDatasourceApplicationConfig.postgresqlDataSource ###");
        return DataSourceBuilder.create().build();
    }*/
    
    @Bean(name = "openedgeDataSource")
    @Primary
    @ConfigurationProperties(prefix="datasource.openedge")
    public DataSource openedgeDataSource() {
    	System.out.println("### Starting Ending MultiDatasourceApplicationConfig.openedgeDataSource ###");
        return DataSourceBuilder.create().build();
    }
}
