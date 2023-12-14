package com.deleteevent.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.deleteevent.dto.EventDTO;

public interface EventService {
	public void deleteEvents(List<Long> eventId);
	public Page<EventDTO> getEvents(Pageable pageable);
}
