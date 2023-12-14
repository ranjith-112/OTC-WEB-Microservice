package com.login.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
@Data
@Entity
@Table(name = "employeedetails")
public class User {
	@Id
	private Long empno;
	private String username;
	private String password;
	private String department;
//	@Column(name = "Trainer")
//	private String Trainer;
	private boolean isorganiser;
	@Transient
	private String[] admin;
}
