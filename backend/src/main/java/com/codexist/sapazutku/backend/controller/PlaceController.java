package com.codexist.sapazutku.backend.controller;

import com.codexist.sapazutku.backend.dto.PlaceDto;
import com.codexist.sapazutku.backend.dto.PlaceResponse;
import com.codexist.sapazutku.backend.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceController {
    private PlaceService placesService;
    @Autowired
    PlaceController(PlaceService placesService) {
        this.placesService = placesService;
    }

    @GetMapping
    public ResponseEntity<PlaceResponse> getPlaces(@RequestParam double latitude, @RequestParam double longitude,
                                                   @RequestParam double radius) {
        try {
            List<PlaceDto> places = placesService.getPlaces(latitude, longitude, radius);
            PlaceResponse placeResponse = new PlaceResponse(places);
            return ResponseEntity.ok(placeResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}