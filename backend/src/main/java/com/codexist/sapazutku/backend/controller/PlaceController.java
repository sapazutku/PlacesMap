package com.codexist.sapazutku.backend.controller;

import com.codexist.sapazutku.backend.dto.PlaceDto;
import com.codexist.sapazutku.backend.dto.PlaceResponse;
import com.codexist.sapazutku.backend.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@CrossOrigin(origins = "http://localhost:3000")
public class PlaceController {
    private final PlaceService placesService;
    @Autowired
    PlaceController(PlaceService placesService) {
        this.placesService = placesService;
    }

    @GetMapping
    public ResponseEntity<PlaceResponse> getPlaces(@RequestParam double latitude, @RequestParam double longitude,
                                                   @RequestParam double radius) {

            List<PlaceDto> places = placesService.getPlaces(latitude, longitude, radius);
            PlaceResponse placeResponse = new PlaceResponse(places);
            return ResponseEntity.ok(placeResponse);

    }
}