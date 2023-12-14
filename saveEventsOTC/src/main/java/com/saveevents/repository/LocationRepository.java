package com.saveevents.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saveevents.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
	Location findByLocation(String location);

}
