package com.equimove.backend.entity;

import java.io.Serializable;

public interface IGenericEntity<PK extends Serializable> {

	PK getPk();
	void setPk(PK pk);
}
