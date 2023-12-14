package com.getallevent.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
@Entity
@Table(name = "event")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String eventname;

	private String details;

	private String startdate;
	
	private int duration;

	private String trainer;

	private String domain;

	private String status;
	@CreatedDate
	private Date created_date_time;

	private String createdby;

	private String modifiedby;
	private boolean isdeleted;

	private String filename;
	@Lob
	private byte[] file;
	@Transient
	private String location;
	private Long locationID;
	private LocalDateTime modified_date_time;
	@Transient
	private String[] Trainers;
	@Transient
	private String[] locations;

}
