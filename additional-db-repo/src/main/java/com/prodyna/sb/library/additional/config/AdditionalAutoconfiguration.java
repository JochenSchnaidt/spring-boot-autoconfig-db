package com.prodyna.sb.library.additional.config;

import com.prodyna.sb.library.shared.config.PostgresEmfPackages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * This configuration makes the entity type 'Survey' available on Postgres.
 */
@Slf4j
@Configuration
@EnableJpaRepositories(
    basePackages = "com.prodyna.sb.library.additional.persistence.repository",
    entityManagerFactoryRef = "postgresEntityManagerFactory",
    transactionManagerRef = "postgresTransactionManager")
public class AdditionalAutoconfiguration {

  @PostConstruct
  public void setup() {
    log.info("HELLO HELLO, " + this.getClass().getName() + "  found");
  }

  @Bean("additionalPostgresEmfPackages")
  public PostgresEmfPackages createPostgresEmfPackages() {
    return new PostgresEmfPackages(List.of("com.prodyna.sb.library.additional.persistence.model"));
  }

}
