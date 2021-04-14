package com.prodyna.sb.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan(basePackages={"com.prodyna.sb.library"})
public class SchedulerAutoconfiguration {

  @Bean
  public ThreadPoolTaskScheduler databaseScheduler() {
    ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    threadPoolTaskScheduler.setPoolSize(3);
    threadPoolTaskScheduler.setThreadNamePrefix("database");
    return threadPoolTaskScheduler;
  }

  @Bean
  public PeriodicTrigger databaseTrigger() {
    PeriodicTrigger periodicTrigger = new PeriodicTrigger(1500, TimeUnit.MILLISECONDS);
    periodicTrigger.setFixedRate(true);
    periodicTrigger.setInitialDelay(2000);
    return periodicTrigger;
  }

}
