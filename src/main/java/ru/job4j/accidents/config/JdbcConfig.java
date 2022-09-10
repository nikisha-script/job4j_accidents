package ru.job4j.accidents.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class JdbcConfig {

    @Bean
    public DataSource getDataSource(@Value("${jdbc.driver}") String driver,
                                    @Value("${jdbc.url}") String url,
                                    @Value("${jdbc.username}") String username,
                                    @Value("${jdbc.password}") String password) {
        BasicDataSource basic = new BasicDataSource();
        basic.setDriverClassName(driver);
        basic.setUsername(username);
        basic.setPassword(password);
        basic.setUrl(url);
        return basic;
    }

    @Bean
    public JdbcTemplate jdbc(DataSource ds) {
        return new JdbcTemplate(ds);
    }

}
