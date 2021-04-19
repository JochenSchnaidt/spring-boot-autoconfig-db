package com.prodyna.sb.library.postgres.config;


import com.prodyna.sb.library.postgres.persistence.repository.PersonalRepository;
import com.prodyna.sb.library.shared.persistence.model.Personal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


@Slf4j
@Component
public class InitializePostgresDatabase {

  @Autowired
  private PersonalRepository personalRepository;

  @PostConstruct
  public void setup() {

    Personal p0 = new Personal(35, "Software Developer", "AT", "Bootcamp");
    Personal p1 = new Personal(42, "Software Developer", "AT", "M. Sc.");
    Personal p2 = new Personal(43, "Musician", "FR", "B. Sc.");
    Personal p3 = new Personal(44, "Baker", "FR", "-");
    Personal p4 = new Personal(45, "Software Architect", "DE", "-");
    Personal p5 = new Personal(46, "Software Architect", "AT", "-");
    Personal p6 = new Personal(46, "Software Developer", "EN", "B. Eng.");
    Personal p7 = new Personal(33, "Software Developer", "AT", "B. Eng.");
    Personal p8 = new Personal(35, "Software Developer", "AT", "B. Sc.");
    Personal p9 = new Personal(35, "Software Developer", "AT", "B. Sc.");

    List<Personal> list = List.of(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    List<Personal> saved = personalRepository.saveAll(list);

    log.info("initialized postgres database with {} entities.", saved.size());
  }

}
