package com.prodyna.sb.library.trigger;

import com.prodyna.sb.library.postgres.persistence.model.Personal;
import com.prodyna.sb.library.postgres.persistence.repository.PersonalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Performer implements Runnable {

  @Autowired
  private PersonalRepository personalRepository;

  @Autowired
  @Qualifier("FooPersonalRepository")
  private com.prodyna.sb.library.mysql.persistence.repository.PersonalRepository mysqlPersonalRepository;

  @Override
  public void run() {
    log.info("- - - perform something - - -");
    Optional<Personal> byId = personalRepository.findById(1L);
    Personal personal = byId.get();
    log.info("gotcha: {}", personal);

    com.prodyna.sb.library.mysql.persistence.model.Personal p = new com.prodyna.sb.library.mysql.persistence.model.Personal();
    p.setAge(personal.getAge());
    p.setCountry(personal.getCountry());
    p.setEducation(personal.getEducation());
    p.setProfession(personal.getProfession());
    com.prodyna.sb.library.mysql.persistence.model.Personal save = mysqlPersonalRepository.save(p);
    log.info("saved: {}", save);
  }
}