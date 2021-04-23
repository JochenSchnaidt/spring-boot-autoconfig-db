package com.prodyna.sb.library.common.trigger;

import com.prodyna.sb.library.additional.persistence.model.Survey;
import com.prodyna.sb.library.additional.persistence.repository.SurveyRepository;
import com.prodyna.sb.library.common.service.MysqlPersonalService;
import com.prodyna.sb.library.common.service.PostgresPersonalService;
import com.prodyna.sb.library.shared.persistence.model.Personal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Performer implements Runnable {

  @Autowired
  private PostgresPersonalService postgresPersonalService;

  @Autowired
  private MysqlPersonalService mysqlPersonalService;

  @Autowired
  private SurveyRepository surveyRepository;

  private static long counter = 1;


  @Transactional
  public void save() {
    log.info("- - - Read from Postgres and write into MySQL - - -");
    Personal foundPostgresPersonal = postgresPersonalService.find(counter++);
    log.info("Found in Postgres: {}", foundPostgresPersonal);

//    saveToMySql(foundPostgresPersonal);
    saveToPostgres(foundPostgresPersonal);
    throw new RuntimeException(("ups"));
  }

  // will get rolled back with Exception thrown
  private void saveToPostgres(Personal postgresPersonal) {
    Personal personal = new Personal();
    personal.setAge(999);
    personal.setCountry(postgresPersonal.getCountry() + ", but not gonna happen");
    personal.setEducation(postgresPersonal.getEducation() + ",  but not gonna happen");
    personal.setProfession(postgresPersonal.getProfession() + ", but not gonna happen");
    Personal savedPostgresPersonal = postgresPersonalService.save(personal);
    log.info("Saved to Postgres: {}", savedPostgresPersonal);
  }

  // will work even with Exception thrown
  private void saveToMySql(Personal postgresPersonal) {
    Personal mysqlPersonal = new Personal();
    mysqlPersonal.setAge(postgresPersonal.getAge());
    mysqlPersonal.setCountry(postgresPersonal.getCountry());
    mysqlPersonal.setEducation(postgresPersonal.getEducation());
    mysqlPersonal.setProfession(postgresPersonal.getProfession());
    Personal savedMysqlPersonal = mysqlPersonalService.save(mysqlPersonal);
    log.info("Saved to MySQL: {}", savedMysqlPersonal);
  }

  @Override
  public void run() {
    try {
      log.info("Before tx save: {}", mysqlPersonalService.findAll());
      save();
    } catch( Exception e) {
      log.error("Ups", e);
    }

    log.info("After tx save: {}", mysqlPersonalService.findAll());


    if (counter >= 10) {
      counter = 1;
    }
  }
}