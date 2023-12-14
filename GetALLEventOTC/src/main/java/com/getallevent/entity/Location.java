package com.getallevent.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Table(name = "location")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Location {
	@Id
	private Long locationID;
	private String location;
}
