package com.prodyna.sb.library.additional.persistence.repository;


import com.prodyna.sb.library.additional.persistence.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
