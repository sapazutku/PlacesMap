package com.codexist.sapazutku.backend.repository;

import com.codexist.sapazutku.backend.model.Location;
import com.codexist.sapazutku.backend.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByLocation(Location location);
}
