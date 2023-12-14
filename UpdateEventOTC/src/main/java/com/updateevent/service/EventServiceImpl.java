package com.updateevent.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.updateevent.dto.EventDTO;
import com.updateevent.entity.Event;
import com.updateevent.entity.Location;
import com.updateevent.exceptionhandling.DataBaseDownException;
import com.updateevent.exceptionhandling.EventServiceException;
import com.updateevent.repository.EventRespository;
import com.updateevent.repository.LocationRepository;

@Service
public class EventServiceImpl implements EventService{
	Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
	@Autowired
	private EventRespository eventRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public void updateEvent(EventDTO eventDTO,String username) {
	
			try {
				//logger.debug("User: {}", username);
				System.out.println("Domain in update :" + eventDTO.getDomain());
				String originalDomainValue = eventDTO.getDomain();
				String modifiedDomainValue = originalDomainValue.replace(",", "");
				String locationname = eventDTO.getLocation();
				Location location = locationRepository.findByLocation(locationname);
				// Fetch the existing event by its ID
				Event existingEvent = eventRepository.findById(eventDTO.getId())
						.orElseThrow(() -> new EventServiceException("Event not found.", HttpStatus.NOT_FOUND));

				// Update the existing event with new data
				existingEvent.setEventname(eventDTO.getEventname());
				existingEvent.setDetails(eventDTO.getDetails());
				existingEvent.setStartdate(eventDTO.getStartdate());
				existingEvent.setDuration(eventDTO.getDuration());
				existingEvent.setTrainer(eventDTO.getTrainer());
				existingEvent.setDomain(modifiedDomainValue);

				existingEvent.setStatus(eventDTO.getStatus());

				existingEvent.setModifiedby(username);
				existingEvent.setModified_date_time(LocalDateTime.now());
				existingEvent.setLocationID(location.getLocationID());

				logger.info("Exisiting Event ", existingEvent.getDomain());
				System.out.println("Exisiting Event "+existingEvent.toString());
				// Save the updated event
				eventRepository.save(existingEvent);

			} catch (DataIntegrityViolationException e) {
				/*
				 * logger.debug("DataIntegrityViolationException",e.getMessage()); throw new
				 * EventServiceException("A record with the same name, start date, and trainer already exists."
				 * , HttpStatus.CONFLICT);
				 */
				String errorMessage = e.getMostSpecificCause().getMessage();
				logger.debug("error : ", errorMessage);

				if (errorMessage.contains("Duplicate entry") && errorMessage.contains("for key 'name'")) {
					throw new EventServiceException("A record with the same name, start date, and trainer already exists.",
							HttpStatus.CONFLICT);
				} else if (errorMessage.contains("Duplicate entry")
						&& errorMessage.contains("for key 'UNIQUE_location_startdate'")) {
					throw new EventServiceException("Location has been already booked", HttpStatus.CONFLICT);
				} else {
					logger.error("DataIntegrityViolationException: {}", errorMessage);
					throw new EventServiceException("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} catch (DataAccessException e) {
				logger.debug("DataAccessException", e.getMessage());
				throw new DataBaseDownException("Database is Down.");
			}

			catch (Exception e) {
				logger.error("Error while saving the event: {}", e.getMessage());
				throw new EventServiceException("Error while Updating the event. Please check correct input data.",
						HttpStatus.BAD_REQUEST);
			}
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public Event getExistingEvent(long eventId) {
		
		Event existingEvent= eventRepository.findById(eventId).orElseThrow(() -> new EventServiceException("Event not found.", HttpStatus.NOT_FOUND));
		long locationid=existingEvent.getLocationID();
		Location location=locationRepository.findById(locationid).orElseThrow(() -> new EventServiceException("location ID not found.", HttpStatus.NOT_FOUND));
		existingEvent.setLocation(location.getLocation());
		
		return existingEvent;
		// TODO Auto-generated method stub
		
	}
	

}
