package com.codexist.sapazutku.backend.repository;

import com.codexist.sapazutku.backend.model.Location;
import com.codexist.sapazutku.backend.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByLocation(Location location);
}
