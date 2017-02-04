package com.model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.PrePersist;

import org.springframework.stereotype.Component;

@Component
public class AbstractEntityListener {

	@PrePersist
	public void prePersist(AbstractEntity abstractEntity) {
		abstractEntity.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));

		InetAddress ipAddress;
		try {
			ipAddress = InetAddress.getLocalHost();
			abstractEntity.setIpAddress(ipAddress.toString());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

	// @PreUpdate
	// public void preUpdate(AbstractEntity abstractEntity) {
	//
	// }

}