package com.equimove.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "AccessToken")
@Table(name = "accesstoken")
@Data
public class AccessTokenEntity implements IGenericEntity<String> {

	@Id
	@Column(name = "ACTK_token")
	private String pk;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ACTK_USER_pk")
	private UserEntity user;
}