package com.prodyna.sb.library.common.trigger;

import com.prodyna.sb.library.common.service.MysqlPersonalService;
import com.prodyna.sb.library.common.service.PostgresPersonalService;
import com.prodyna.sb.library.postgres.persistence.model.Personal;
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

    private static long counter = 1;

    @Override
    public void run() {
        log.info("- - - Read from Postgres and write into MySQL - - -");
        Personal personal = postgresPersonalService.find(counter++);
        log.info("Found in Postgres: {}", personal);

        com.prodyna.sb.library.mysql.persistence.model.Personal p = new com.prodyna.sb.library.mysql.persistence.model.Personal();
        p.setAge(personal.getAge());
        p.setCountry(personal.getCountry());
        p.setEducation(personal.getEducation());
        p.setProfession(personal.getProfession());
        com.prodyna.sb.library.mysql.persistence.model.Personal save = mysqlPersonalService.save(p);
        log.info("Saved to MySQL: {}", save);

        if (counter >= 10) {
            counter = 1;
        }
    }
}