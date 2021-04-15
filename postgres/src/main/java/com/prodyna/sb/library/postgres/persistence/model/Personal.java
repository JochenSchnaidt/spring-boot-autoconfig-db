package com.prodyna.sb.library.postgres.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class Personal implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
  @GenericGenerator(name = "hibernate_sequence", strategy = "native")
  private Long id;

  private Integer age;
  private String profession;
  private String country;
  private String education;

  public Personal(Integer age, String profession, String country, String education) {
    this.age = age;
    this.profession = profession;
    this.country = country;
    this.education = education;
  }
}
