package com.ms.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 2174546004713445212L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "userId")
	private String userId;
	@Column(unique = true)
	private String email;
	private String contactNumber;
	private String password;
	private String firstName;
	private String lastName;
	@Column(precision = 10, scale = 2, name= "accountBalance")
	private double accountBalance;
	private boolean active;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private Set<RoleEntity> roles;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_addresses",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "addressId")
    )
    private Set<AddressEntity> addresses;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSERT_DT", nullable = false)
	private Date insertDate;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DT", nullable = false)
	private Date updateDate;
}
