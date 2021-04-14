package com.prodyna.sb.library.postgres.persistence.repository;


import com.prodyna.sb.library.postgres.persistence.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
}
