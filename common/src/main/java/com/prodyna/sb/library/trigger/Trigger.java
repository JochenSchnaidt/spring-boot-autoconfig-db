package com.prodyna.sb.library.trigger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Trigger {

  @Autowired
  private Performer performer;

  @Autowired
  private ThreadPoolTaskScheduler databaseScheduler;

  @Autowired
  private PeriodicTrigger databaseTrigger;

  @PostConstruct
  public void go() {
    databaseScheduler.schedule(performer, databaseTrigger);
  }

}
