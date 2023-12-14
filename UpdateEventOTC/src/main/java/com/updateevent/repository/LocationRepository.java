package com.updateevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.updateevent.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
	Location findByLocation(String location);

}
