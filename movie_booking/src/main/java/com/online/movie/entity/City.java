package com.online.movie.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties
public class City implements Serializable {

  private static final long serialVersionUID = 4345155470400908407L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long cityId;

  private String name;

  private String country;

  private String state;

  private Double latitude;

  private Double longtitude;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @CreatedBy private String createdBy;
}
