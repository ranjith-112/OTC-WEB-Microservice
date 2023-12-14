package com.deleteevent.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deleteevent.dto.EventDTO;
import com.deleteevent.exceptionhandling.EventServiceException;
import com.deleteevent.service.EventService;

@RestController
public class Eventcontroller {
	Logger logger = LoggerFactory.getLogger(Eventcontroller.class);
	@Autowired
	private EventService eventservice;

	@PreAuthorize("hasAuthority('admin')")
	@RequestMapping(value = "events/deleteByEventId",method = RequestMethod.POST)
	public ResponseEntity<Object> deleteEvent(@RequestBody List<Long> eventId) {
		logger.debug("getting id from front-end for delete event :{}", eventId);
		try {
			eventservice.deleteEvents(eventId);
		} catch (Exception e) {
			System.out.println("exceptiom" +e);
			throw new EventServiceException("Cannot delete the records.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(Collections.singletonMap("message", "Events has been Successfully Deleted"), HttpStatus.OK);

		//return new ResponseEntity<>.ok().body("successfully deleted");
	}
	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("events/getAllEvents")
	public Page<EventDTO> getAllEvents(Pageable pageable) {
		//System.out.println("isProxy() " + AopUtils.isAopProxy(eventservice));
		return eventservice.getEvents(pageable);
	}
}
