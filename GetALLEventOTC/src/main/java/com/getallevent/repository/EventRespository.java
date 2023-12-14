package com.getallevent.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.getallevent.entity.Event;

public interface EventRespository extends JpaRepository<Event, Long> {

	@Query("select e from Event e where isdeleted=0")
	Page<Event> findByIsDeleted(Pageable paging);
}
