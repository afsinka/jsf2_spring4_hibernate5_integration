package com.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
@Access(AccessType.FIELD)
@EntityListeners(AbstractEntityListener.class)
public abstract class AbstractEntity implements Serializable {

	protected static final String SEQUENCE_GENERATOR_NAME = "SP_SEQUENCE_GENERATOR";

	@Version
	private Integer version;

	@Column(name = "IP_ADDRESS")
	private String ipAddress;

	@Column(name = "DATE_CREATED", columnDefinition="TIMESTAMP")
	private Timestamp creationDate;

	public abstract Long getId();

	public abstract void setId(Long id);

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

}