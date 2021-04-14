package com.prodyna.sb.library.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableConfigurationProperties({PostgresProperties.class})
@EnableTransactionManagement()
@ComponentScan(basePackages={"com.prodyna.sb.library"})
@EnableJpaRepositories(basePackages = "com.prodyna.sb.library.postgres.persistence.repository",
    entityManagerFactoryRef="postgresEntityManagerFactory",  transactionManagerRef = "postgresTransactionManager")
public class PostgresAutoconfiguration {

  @Autowired
  private PostgresProperties postgresProperties;

  @Bean
  @Primary
  public DataSource postgresDataSource() {
    HikariConfig config = new HikariConfig();
    HikariDataSource dataSource;

    config.setJdbcUrl(postgresProperties.getUrl());
    config.setUsername(postgresProperties.getUsername());
    config.setPassword(postgresProperties.getPassword());
    // optional: Property setting depends on database vendor
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    dataSource = new HikariDataSource(config);
    return dataSource;
  }

  @Bean
  @Primary
  // @PersistenceContext(unitName = "postgres")
  public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(DataSource postgresDataSource) {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(postgresDataSource);
    em.setPackagesToScan("com.prodyna.sb.library.postgres.persistence.model");

    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
     em.setJpaVendorAdapter(hibernateJpaVendorAdapter);

    Properties properties = new Properties();
    properties.put("hibernate.hbm2ddl.auto", "update");
    properties.setProperty("open-in-view", "false");
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    properties.setProperty("show-sql", "true");
    em.setJpaProperties(properties);
    return em;
  }

  @Bean
  @Primary
  JpaTransactionManager postgresTransactionManager(EntityManagerFactory postgresEntityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(postgresEntityManagerFactory);
    return transactionManager;
  }

}
