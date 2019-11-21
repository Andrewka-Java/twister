package by.twister;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;

@Configuration
@ContextConfiguration
public class DataBaseConfig {

    @Value("${datasource.url}")
    private String DB_URL;

    @Value("${datasource.driver}")
    private String DB_DRIVER;

    @Value("${datasource.user}")
    private String DB_USER;

    @Value("${datasource.password}")
    private String DB_PASSWORD;



    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWORD);

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();

        txManager.setDataSource(dataSource());

        return txManager;
    }


}