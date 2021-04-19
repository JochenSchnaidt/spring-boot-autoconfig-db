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

  @Override
  public void run() {
    log.info("- - - Read from Postgres and write into MySQL - - -");
    Personal postgresPersonal = postgresPersonalService.find(counter++);
    log.info("Found in Postgres: {}", postgresPersonal);

    Personal mysqlPersonal = new Personal();
    mysqlPersonal.setAge(postgresPersonal.getAge());
    mysqlPersonal.setCountry(postgresPersonal.getCountry());
    mysqlPersonal.setEducation(postgresPersonal.getEducation());
    mysqlPersonal.setProfession(postgresPersonal.getProfession());
    Personal savedMysqlPersonal = mysqlPersonalService.save(mysqlPersonal);
    log.info("Saved to MySQL: {}", savedMysqlPersonal);

    if (counter == 5) {
      Survey survey = new Survey();
      survey.setAccountOwner(true);
      survey.setCommunityMember(false);
      survey.setParticipationFrequency("often");
      survey.setVisitationFrequency("even more often");
      survey.setEase("easy");
      survey.setLength("awful long");
      Survey savedSurvey = surveyRepository.save(survey);
      log.info("Saved to Postgres: {}", savedSurvey);
    }

    if (counter >= 10) {
      counter = 1;
    }
  }
}