package com.codexist.sapazutku.backend.repository;

import com.codexist.sapazutku.backend.dto.LocationDto;
import com.codexist.sapazutku.backend.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

}
