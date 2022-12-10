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
@EnableJpaRepositories(entityManagerFactoryRef = "gs001001EntityManagerFactory", transactionManagerRef = "gs001001TransactionManager", basePackages = {
        "com.qapaq.gs00100.jpa.queries",
})
public class SchemaGS001001 {

    @Autowired
    private Environment env;

    @Bean(name="gs001001DataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSourceGS = new DriverManagerDataSource();
        String className = env.getProperty("GS_001_00.datasource.driver-class-name");
        
        if (className != null) {
            dataSourceGS.setDriverClassName(className);    
        }        
        dataSourceGS.setUrl(env.getProperty("GS_001_00.datasource.url"));
        dataSourceGS.setUsername(env.getProperty("GS_001_00.datasource.username"));
        dataSourceGS.setPassword(env.getProperty("GS_001_00.datasource.password"));
        
        return dataSourceGS;
    }

    @Bean(name="gs001001EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emGS = new LocalContainerEntityManagerFactoryBean();
        emGS.setDataSource(dataSource());
        emGS.setPackagesToScan("com.qapaq.gs00100.jpa.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emGS.setJpaVendorAdapter(vendorAdapter);

        
        Map<String, Object> propertiesGS = new HashMap<>();
        propertiesGS.put("spring.jpa.show-sql", env.getProperty("GS_001_00.jpa.show-sql"));
        propertiesGS.put("spring.jpa.properties.hibernate.format_sql", env.getProperty("GS_001_00.jpa.properties.hibernate.format_sql"));
        propertiesGS.put("spring.jpa.properties.hibernate.dialect", env.getProperty("GS_001_00.jpa.properties.hibernate.dialect"));

        emGS.setJpaPropertyMap(propertiesGS);
        
        return emGS;
    }

    @Bean(name="gs001001TransactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }
}
