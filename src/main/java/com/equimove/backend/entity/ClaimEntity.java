package com.equimove.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "Claim")
@Table(name = "claim")
@Data
public class ClaimEntity implements IGenericEntity<Long> {

	private final static String PK_SEQUENCE = "CLAIM_pk_seq";

	@Id
	@SequenceGenerator(name = PK_SEQUENCE, sequenceName = PK_SEQUENCE, initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PK_SEQUENCE)
	@Column(name = "CLAIM_pk")
	private Long pk;

	@Column(name = "CLAIM_message")
	private String message;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CLAIM_Claimant_pk")
	private UserEntity claimant;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CLAIM_Horse_pk")
	private HorseEntity horse;
}
