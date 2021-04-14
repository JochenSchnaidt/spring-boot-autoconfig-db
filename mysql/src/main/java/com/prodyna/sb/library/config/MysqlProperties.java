package com.prodyna.sb.library.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "database.mysql")
public class MysqlProperties {

  private String username = "bruce_wayne";
  private String password = "i_am_batman";
  private String driverClassName = "com.mysql.jdbc.Driver";
  private String url = "jdbc:mysql://localhost:3306/db";

}