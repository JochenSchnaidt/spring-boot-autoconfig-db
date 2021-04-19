package com.prodyna.sb.library.additional.persistence.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Survey implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
  @GenericGenerator(name = "hibernate_sequence", strategy = "native")
  private Long id;

  private Boolean accountOwner;
  private Boolean communityMember;
  private String participationFrequency;
  private String visitationFrequency;
  private String ease;
  private String length;

}
