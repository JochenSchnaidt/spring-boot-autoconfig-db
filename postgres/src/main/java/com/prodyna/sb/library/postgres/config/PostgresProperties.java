package com.prodyna.sb.library.postgres.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "database.postgres")
public class PostgresProperties {

  private String username = "bruce_wayne";
  private String password = "i_am_batman";
  private String driverClassName = "org.postgresql.Driver";
  private String url = "jdbc:postgresql://localhost:5432/postgres";

}
