package com.updateevent.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.updateevent.entity.Event;

public interface EventRespository extends JpaRepository<Event, Long>{
	@Transactional
	@Modifying
	@Query("update Event set isdeleted=1 where id in(:eventsId)")
	public void deleteByIsdeleted(List<Long> eventsId);

	
	@Query("select e from Event e where isDeleted=0")
	Page<Event> findByIsDeleted(Pageable paging);
}
