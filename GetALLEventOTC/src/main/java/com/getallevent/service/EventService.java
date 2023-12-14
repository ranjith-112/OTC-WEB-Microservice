package com.getallevent.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.getallevent.dto.EventDTO;

public interface EventService {
	
	public Page<EventDTO> getEvents(Pageable pageable);
}
