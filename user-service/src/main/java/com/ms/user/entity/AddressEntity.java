package com.ms.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "addresses")
public class AddressEntity implements Serializable {

	private static final long serialVersionUID = -1204547714516887226L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "addressId")
	private long id;
	private String houseNo;
	private String apartmentName;
	private String fullAddress;
	private String pincode;
	private String city;
	private String state;
	private String country;
	private boolean active;
	private boolean defaultAddress;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSERT_DT", nullable = false)
	private Date insertDate;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DT", nullable = false)
	private Date updateDate;
}
