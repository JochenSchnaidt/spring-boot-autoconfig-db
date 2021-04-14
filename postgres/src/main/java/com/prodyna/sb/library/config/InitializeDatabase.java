package com.prodyna.sb.library.config;

import com.prodyna.sb.library.postgres.persistence.model.Personal;
import com.prodyna.sb.library.postgres.persistence.repository.PersonalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Slf4j
@Component
public class InitializeDatabase {

  @Autowired
  private PersonalRepository personalRepository;

  @PostConstruct
  public void setup() throws IOException {

    Personal p = new Personal();
    p.setAge(42);
    p.setCountry("DE");
    p.setEducation("foo");
    p.setProfession("bar");
    personalRepository.save(p);

    log.info("initialized database");
  }

}
