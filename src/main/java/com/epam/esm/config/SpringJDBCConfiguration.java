package com.epam.esm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * This configuration class use profiling.
 * DataSource implementing H2 or MYSQL configuration depending on the profile(DEV/PROD)
 **/

@Configuration
//@EnableTransactionManagement

@PropertySources({
        @PropertySource("classpath:/application-prod.properties")
//        ,
//        @PropertySource(value = "classpath:/application-dev.properties")
})
public class SpringJDBCConfiguration {

    @Autowired
    ConfigUtility configUtility;

    @Bean
public DataSource getDatasource(){
    org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
    ds.setDriverClassName(configUtility.getProperty("spring.datasource.driverClassName"));
    ds.setUrl(configUtility.getProperty("spring.datasource.url"));
    ds.setUsername(configUtility.getProperty("spring.datasource.username"));
    ds.setPassword(configUtility.getProperty("spring.datasource.password"));
        return ds;

}

    @Bean
    public DataSourceTransactionManager transactionManager() {
        final DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(getDatasource());
        return txManager;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(getDatasource());
    }
}
