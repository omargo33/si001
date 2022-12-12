package com.qapaq.gs00100.configuracion;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

    @Bean(name = "ar001001DataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSourceAR = new DriverManagerDataSource();
        String className = env.getProperty("AR_001_00.datasource.driver-class-name");

        if (className != null) {
            dataSourceAR.setDriverClassName(className);
        }
        dataSourceAR.setUrl(env.getProperty("AR_001_00.datasource.url"));
        dataSourceAR.setUsername(env.getProperty("AR_001_00.datasource.username"));
        dataSourceAR.setPassword(env.getProperty("AR_001_00.datasource.password"));

        return dataSourceAR;
    }

    @Bean(name="ar001001EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        String showSQL = env.getProperty("AR_001_00.jpa.show-sql");
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean emAR = new LocalContainerEntityManagerFactoryBean();
        emAR.setDataSource(dataSource());
        emAR.setPackagesToScan("com.qapaq.ar00100.jpa.model");

        if (showSQL != null && !showSQL.isEmpty() && showSQL.equals("true")) {
            jpaVendorAdapter.setShowSql(true);
        }

        emAR.setJpaVendorAdapter(jpaVendorAdapter);     
        emAR.setJpaPropertyMap(additionalMapProperties());   
        return emAR;
    }    

        /**
     * Metodo para agreger propiedades adicionales a la configuracion de JPA
     * 
     * @return
     */
    private Map<String, Object> additionalMapProperties() {
        Map<String, Object> map = new HashMap<>();        
        
        map.put("hibernate.dialect", 
            env.getProperty("AR_001_00.jpa.properties.hibernate.dialect"));
        map.put("hibernate.format_sql", 
            env.getProperty("AR_001_00.jpa.properties.hibernate.format_sql"));
        map.put("hibernate.enable_lazy_load_no_trans", 
            env.getProperty("AR_001_00.jpa.properties.hibernate.enable_lazy_load_no_trans"));

        return map;
    }


    @Bean(name = "ar001001TransactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
