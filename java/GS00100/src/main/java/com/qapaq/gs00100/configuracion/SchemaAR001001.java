package com.qapaq.gs00100.configuracion;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "ar001001EntityManagerFactory", transactionManagerRef = "ar001001TransactionManager", basePackages = {
        "com.qapaq.ar00100.jpa.queries",
})
public class SchemaAR001001 {

    @Autowired
    private Environment env;

    @Bean(name="ar001001DataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("AR_001_00.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("AR_001_00.datasource.url"));
        dataSource.setUsername(env.getProperty("AR_001_00.datasource.username"));
        dataSource.setPassword(env.getProperty("AR_001_00.datasource.password"));
        
        return dataSource;
    }

    @Bean(name="ar001001EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.qapaq.ar00100.jpa.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new  HashMap<>();
        properties.put("spring.jpa.show-sql", env.getProperty("AR_001_00.jpa.show-sql"));
        properties.put("spring.jpa.properties.hibernate.format_sql", env.getProperty("AR_001_00.jpa.properties.hibernate.format_sql"));
        properties.put("spring.jpa.properties.hibernate.dialect", env.getProperty("AR_001_00.jpa.properties.hibernate.dialect"));

        em.setJpaPropertyMap(properties);
        
        return em;
    }

    @Bean(name="ar001001TransactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
}
