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
@Table(name = "roles")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 1927626444926836970L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "roleId")
	private long id;
	private String roleName;
	private String roleDesc;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSERT_DT", nullable = false)
	private Date insertDate;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DT", nullable = false)
	private Date updateDate;
}
