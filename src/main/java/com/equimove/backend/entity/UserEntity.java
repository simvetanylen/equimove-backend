package com.equimove.backend.entity;

import com.equimove.backend.constant.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "User")
@Table(name = "user")
@Data
public class UserEntity implements IGenericEntity<Long> {

	private final static String PK_SEQUENCE = "USER_pk_seq";

	@Id
	@SequenceGenerator(name = PK_SEQUENCE, sequenceName = PK_SEQUENCE, initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PK_SEQUENCE)
	@Column(name = "USER_pk")
	private Long pk;

	@Transient
	private UserType type;

	@Column(name = "USER_email")
	@JsonIgnore
	private String email;

	@Column(name = "USER_firstName")
	private String firstName;

	@Column(name = "USER_lastName")
	private String lastName;

	@Column(name = "USER_password")
	@JsonIgnore
	private String password;
}
