package com.updateevent.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.updateevent.dto.EventDTO;
import com.updateevent.entity.Event;
import com.updateevent.service.EventService;

@RestController
public class Eventcontroller {
	Logger logger = LoggerFactory.getLogger(Eventcontroller.class);
	@Autowired
	private EventService eventservice;

	@PreAuthorize("hasAuthority('admin')")
	@PostMapping("update/events")
	@CrossOrigin(origins = {"http://localhost:4200/"})
	public ResponseEntity<Map<String, String>> createEvent(@RequestBody EventDTO eventDTO, Principal principal) {
	    System.out.println("gfjdbjdf " + eventDTO);
	    logger.debug("object is ", eventDTO);
	    System.out.println("Principal of username" + principal.getName());
	    eventservice.updateEvent(eventDTO, principal.getName());

	    Map<String, String> response = new HashMap<>();
	    response.put("message", "successfully added");

	    return ResponseEntity.ok().body(response);
	}

//	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("getexisting/events")
	@CrossOrigin(origins = {"http://localhost:4200/"})
	public ResponseEntity<Event> createEvent(@RequestParam ("eventId") long eventId) {
		System.out.println("Received eventId: " + eventId);
		logger.debug("object is ",eventId);
	Event event=	eventservice.getExistingEvent(eventId);
	

    if (event != null) {
        return ResponseEntity.ok().body(event);
    } else {
        return ResponseEntity.notFound().build();
    }
		
	}
}
