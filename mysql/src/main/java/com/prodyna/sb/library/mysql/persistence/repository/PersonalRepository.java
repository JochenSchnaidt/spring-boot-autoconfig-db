package com.prodyna.sb.library.mysql.persistence.repository;

import com.prodyna.sb.library.mysql.persistence.model.Personal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("FooPersonalRepository")
public interface PersonalRepository extends JpaRepository<Personal, Long> {
}
