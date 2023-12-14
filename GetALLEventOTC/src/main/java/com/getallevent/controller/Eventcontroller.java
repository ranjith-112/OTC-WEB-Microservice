package com.getallevent.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getallevent.dto.EventDTO;
import com.getallevent.exceptionhandling.EventServiceException;
import com.getallevent.service.EventService;

@RestController
public class Eventcontroller {
	Logger logger = LoggerFactory.getLogger(Eventcontroller.class);
	@Autowired
	private EventService eventservice;


	@GetMapping("events/getAllEvents")
//	@CrossOrigin(origins = {"http://localhost:4200"})
	public Page<EventDTO> getAllEvents(Pageable pageable) {
		System.out.println("isProxy() " );
		String trainer="aa"; 
		return eventservice.getEvents(pageable);
	}
}
