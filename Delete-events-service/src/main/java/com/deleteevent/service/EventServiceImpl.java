package com.deleteevent.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.deleteevent.dto.EventDTO;
import com.deleteevent.entity.Event;
import com.deleteevent.exceptionhandling.EventServiceException;
import com.deleteevent.repository.EventRespository;

@Service
public class EventServiceImpl implements EventService{
	Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
	@Autowired
	private EventRespository eventRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	@CacheEvict(value = "deleteEvents", key = "#eventId")
	public void deleteEvents(List<Long> eventId) {
	    logger.debug("Deleting events by id: {}", eventId);
	    try {
	        eventRepository.deleteByIsdeleted(eventId);
	    } catch (EmptyResultDataAccessException e) {
	        throw new EventServiceException("Event not found.", HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        logger.error("Error while deleting events: {}", e.getMessage());
	        throw new EventServiceException("Error while deleting events.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@Cacheable(value = "Events" ,key = "#paging")
	public Page<EventDTO> getEvents(Pageable paging) {
		try {
			Page<Event> events = eventRepository.findByIsDeleted(paging);
			
//			// Log the contents of the list
			//logger.info("List of events: {}", events);
//			logger.info("List of events per page displaying: {}", events.getSize());
			return events.map(objectEntity -> modelMapper.map(objectEntity, EventDTO.class));
		}
		catch (Exception e) {
			logger.error("Error while fetching events: {}", e.getMessage());
			throw new EventServiceException("Error while fetching events.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	

}
