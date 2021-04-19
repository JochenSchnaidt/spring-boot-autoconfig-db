package com.prodyna.sb.library.mysql.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableConfigurationProperties({MysqlProperties.class})
@EnableTransactionManagement()
@EnableJpaRepositories(
    basePackages = "com.prodyna.sb.library.mysql.persistence.repository",
    entityManagerFactoryRef = "mysqlEntityManagerFactory",
    transactionManagerRef = "mysqlTransactionManager")
public class MysqlAutoconfiguration {

  @Autowired
  private MysqlProperties mysqlProperties;

  @Bean
  public DataSource mysqlDataSource() {
    HikariConfig config = new HikariConfig();
    HikariDataSource dataSource;

    config.setJdbcUrl(mysqlProperties.getUrl());
    config.setUsername(mysqlProperties.getUsername());
    config.setPassword(mysqlProperties.getPassword());
    // optional: Property setting depends on database vendor
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    dataSource = new HikariDataSource(config);
    return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(@Qualifier("mysqlDataSource") DataSource mysqlDataSource) {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(mysqlDataSource);
    em.setPackagesToScan("com.prodyna.sb.library.shared.persistence.model");

    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(hibernateJpaVendorAdapter);

    Properties properties = new Properties();
    properties.put("hibernate.hbm2ddl.auto", "update");
    properties.setProperty("open-in-view", "false");
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
    properties.setProperty("show-sql", "true");
    em.setJpaProperties(properties);
    return em;
  }

  @Bean
  JpaTransactionManager mysqlTransactionManager(@Qualifier("mysqlEntityManagerFactory") EntityManagerFactory mysqlEntityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(mysqlEntityManagerFactory);
    return transactionManager;
  }

}
