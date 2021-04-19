package com.prodyna.sb.library.mysql.persistence.repository;

import com.prodyna.sb.library.shared.persistence.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("MysqlPersonalRepository")
public interface PersonalRepository extends JpaRepository<Personal, Long> {
}
