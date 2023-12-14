package com.saveevents.dto;

import java.util.Date;

import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data

public class EventDTO {
	private Long id;
	@NotEmpty(message = "{validation.name.NotEmpty}")
	// @Size(min = 3, max = 150)
	@Pattern(regexp = "^[a-z\\sA-Z\\s0-9\\s@\\s!]+$", message = "{validation.name.Pattern}")
	private String eventname;

	@NotEmpty(message = "{validation.name.NotEmpty}")
	@Size(min = 3, max = 600)
	@Pattern(regexp = "^[a-z\\sA-Z\\s0-9\\s@\\s!.,:;-<>/]+$", message = "{validation.name.Pattern.details}")
	private String details;
	private String startdate;

	private int duration;

	@NotEmpty(message = "{validation.name.NotEmpty}")
	@Size(min = 3, max = 150, message = "{validation.name.Size}")
	private String trainer;

	@NotEmpty(message = "{validation.name.NotEmpty}")
	private String domain;

	@NotEmpty(message = "{validation.name.NotEmpty}")
	private String status;
	@CreatedDate
	//private Date created_date_time;
	//private String createdby;

	//private String modifiedby;
	private boolean isdeleted;
	@Transient
	private String filename;
	//private MultipartFile multifilename;
	@Transient
	private byte[] file;
	private String location;
	private Long locationID;
}
