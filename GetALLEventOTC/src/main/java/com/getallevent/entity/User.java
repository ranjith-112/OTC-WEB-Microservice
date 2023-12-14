package com.getallevent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name = "employeedetails")
@Data
public class User {
	@Id
	private Long empno;
	private String username;
	private String password;
	private String department;
	@Column(name = "Trainer")
	private String Trainer;
	private boolean isorganiser;
	@Transient
	private String[] admin;
}
