package com.prodyna.sb.library.postgres.persistence.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Personal implements Serializable {

  @Id
  @GeneratedValue
  private Long id;

  private Integer age;
  private String profession;
  private String country;
  private String education;

}
