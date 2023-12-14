package com.getallevent.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.getallevent.dto.EventDTO;
import com.getallevent.entity.Event;
import com.getallevent.entity.Location;
import com.getallevent.exceptionhandling.EventServiceException;
import com.getallevent.repository.EventRespository;
import com.getallevent.repository.LocationRepository;
import com.getallevent.repository.UserRepository;

@Service
public class EventServiceImpl implements EventService {
	Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
	@Autowired
	private EventRespository eventRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;

	
	public Page<EventDTO> getEvents(Pageable paging) {
		try {
			Page<Event> events = eventRepository.findByIsDeleted(paging);
		
		
			

//			// Log the contents of the list
			// logger.info("List of events: {}", events);
//			logger.info("List of events per page displaying: {}", events.getSize());
			return events.map(objectEntity -> modelMapper.map(objectEntity, EventDTO.class));
		} catch (Exception e) {
			logger.error("Error while fetching events: {}", e.getMessage());
			throw new EventServiceException("Error while fetching events.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
