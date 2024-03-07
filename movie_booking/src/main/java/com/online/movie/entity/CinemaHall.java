package com.online.movie.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import lombok.Data;

@Entity
@Data
public class CinemaHall implements Serializable {

  private static final long serialVersionUID = 1277445220787394604L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long cinemaHallId;

  private String name;

  private String streetAddress;

  @OneToMany(mappedBy = "screenId", cascade = CascadeType.ALL)
  private List<Screen> screens;

  @OneToOne
  @JoinColumn(name = "cityId", nullable = false)
  private City city;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @CreatedBy private String createdBy;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedDate;

  @LastModifiedBy private String updatedBy;
}
