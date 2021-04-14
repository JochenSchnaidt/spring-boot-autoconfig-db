package com.prodyna.sb.library.postgres.persistence.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Personal implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
  // @GenericGenerator(name = "hibernate_sequence", strategy = "native")
  private Long id;

  private Integer age;
  private String profession;
  private String country;
  private String education;

}
