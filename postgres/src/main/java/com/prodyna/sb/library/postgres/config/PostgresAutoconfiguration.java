package com.prodyna.sb.library.postgres.config;

import com.prodyna.sb.library.shared.config.PostgresEmfPackages;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;


@Configuration
// the following line detects the InitializePostgresDatabase class
@ComponentScan(basePackages = "com.prodyna.sb.library.postgres.config")
@EnableConfigurationProperties({PostgresProperties.class})
@EnableTransactionManagement()
@EnableJpaRepositories(
    basePackages = "com.prodyna.sb.library.postgres.persistence.repository",
    entityManagerFactoryRef = "postgresEntityManagerFactory",
    transactionManagerRef = "postgresTransactionManager")
public class PostgresAutoconfiguration {

  @Autowired
  private PostgresProperties postgresProperties;

  @Bean
  public PostgresEmfPackages createPostgresEmfPackages(){
    return new PostgresEmfPackages(List.of("com.prodyna.sb.library.shared.persistence.model"));
  }

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
  public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(DataSource postgresDataSource, List<PostgresEmfPackages> postgresEmfPackages) {

    String[] emfPackages = postgresEmfPackages.stream()
        .map(PostgresEmfPackages::getPackages)
        .flatMap(List::stream)
        .toArray(String[]::new);

    Properties properties = new Properties();
    properties.put("hibernate.hbm2ddl.auto", "update");
    properties.setProperty("open-in-view", "false");
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    properties.setProperty("show-sql", "true");

    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(postgresDataSource);
    em.setPackagesToScan(emfPackages);

    HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(hibernateJpaVendorAdapter);
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
