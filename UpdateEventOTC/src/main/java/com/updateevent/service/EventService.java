package com.updateevent.service;

import com.updateevent.dto.EventDTO;
import com.updateevent.entity.Event;

public interface EventService {

	public void updateEvent(EventDTO eventDTO, String username);

	public Event getExistingEvent(long eventId);
}
