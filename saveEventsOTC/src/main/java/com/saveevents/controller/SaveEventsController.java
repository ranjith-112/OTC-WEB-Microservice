package com.saveevents.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.saveevents.dto.EventDTO;
import com.saveevents.service.EventService;

@RestController
public class SaveEventsController {
	Logger logger = LoggerFactory.getLogger(SaveEventsController.class);
	@Autowired
	private EventService eventservice;                                                     
	@PreAuthorize("hasAuthority('admin')")
	@PostMapping("new/events")
	@CrossOrigin(origins = {"http://localhost:4200/"})
	public ResponseEntity<String> createEvent(@RequestBody EventDTO eventDTO) {
		System.out.println("gfjdbjdf "+eventDTO);
		logger.debug("object is ",eventDTO);
		eventservice.saveEvents(eventDTO);
	
		return  ResponseEntity.ok().body("successfully added");
		
	}
}
