package com.equimove.backend.entity;

import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity(name = "Horse")
@Table(name = "horse")
@Data
public class HorseEntity implements IGenericEntity<Long> {

	private final static String PK_SEQUENCE = "HORSE_pk_seq";

	@Id
	@SequenceGenerator(name = PK_SEQUENCE, sequenceName = PK_SEQUENCE, initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PK_SEQUENCE)
	@Column(name = "HORSE_pk")
	private Long pk;

	@Column(name = "HORSE_name")
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "HORSE_owner_pk")
	private UserEntity owner;
}
