package com.getallevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getallevent.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
	Location findByLocation(String location);
}

