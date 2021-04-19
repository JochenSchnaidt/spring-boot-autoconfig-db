package com.prodyna.sb.library.common.service;


import com.prodyna.sb.library.postgres.persistence.repository.PersonalRepository;
import com.prodyna.sb.library.shared.persistence.model.Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostgresPersonalService implements PersonalService<Personal> {

  @Autowired
  private PersonalRepository personalRepository;

  @Override
  public Personal find(Long id) {
    return personalRepository.findById(id).get();
  }

  @Override
  public Personal save(Personal personal) {
    return personalRepository.save(personal);
  }
}
