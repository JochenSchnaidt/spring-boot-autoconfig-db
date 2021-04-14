package com.prodyna.sb.library.trigger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Performer implements Runnable {

  @Override
  public void run() {
    log.info("- - - perform something - - -");

  }

}