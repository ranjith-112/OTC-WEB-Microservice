package com.saveevents.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.saveevents.dto.EventDTO;
import com.saveevents.entity.Event;
import com.saveevents.entity.Location;
import com.saveevents.exceptionhandling.EventServiceException;
import com.saveevents.repository.EventRespository;
import com.saveevents.repository.LocationRepository;

@Service
public class EventServiceImpl implements EventService{
	Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
	@Autowired
	private EventRespository eventRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private LocationRepository locationRepository;
//	@Override
//	public void saveEvents(EventDTO eventDTO, String username) {
//		String locationname;
//		try {
//			logger.debug("User: {}", username);
//			locationname = eventDTO.getLocation();
//			Location location = locationRepository.findByLocation(locationname);
//			logger.debug("Location ID: ", location.getLocationID());
//			System.out.println("controller in Event location ID : " + location.getLocationID());
//			eventDTO.setLocationID(location.getLocationID());
//			String originalDomainValue = eventDTO.getDomain();
//			String modifiedDomainValue = originalDomainValue.replace(",", "");
//			eventDTO.setCreatedby(username);
//			eventDTO.setDomain(modifiedDomainValue);
//			logger.info("Event DTO :{}", eventDTO);
//			Event event = modelMapper.map(eventDTO, Event.class);
//			logger.debug("location while saving", event.getEventname());
//			logger.debug("Event details: ", event);
//			eventRepository.save(event);
//		} catch (DataIntegrityViolationException e) {
//			String errorMessage = e.getMostSpecificCause().getMessage();
//			logger.debug("error : ", errorMessage);
//
//			if (errorMessage.contains("Duplicate entry") && errorMessage.contains("for key 'name'")) {
//				throw new EventServiceException("A record with the same name, start date, and trainer already exists.",
//						HttpStatus.CONFLICT);
//			} else if (errorMessage.contains("Duplicate entry")
//					&& errorMessage.contains("for key 'UNIQUE_location_startdate'")) {
//				throw new EventServiceException("Location has been already booked", HttpStatus.CONFLICT);
//			} else {
//				logger.error("DataIntegrityViolationException: {}", errorMessage);
//				throw new EventServiceException("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//	}
//	}
//	


	@Override
	public void saveEvents(EventDTO eventDTO) {
		try {
			String locationname = eventDTO.getLocation();
			System.out.println("location Name"+locationname);
			Location location = locationRepository.findByLocation(locationname);
			logger.debug("Location ID: ", location.getLocationID());
			eventDTO.setLocationID(location.getLocationID());
			Event event = modelMapper.map(eventDTO, Event.class);
			eventRepository.save(event);
		}
		catch (DataIntegrityViolationException e) {
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
	}
		
	}
	
	
}
