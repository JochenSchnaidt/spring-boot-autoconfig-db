package com.prodyna.sb.library.common.service;


import com.prodyna.sb.library.mysql.persistence.repository.PersonalRepository;
import com.prodyna.sb.library.shared.persistence.model.Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MysqlPersonalService implements PersonalService<Personal> {

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

    public long findAll() {
        return personalRepository.findAll().size();
    }
}
