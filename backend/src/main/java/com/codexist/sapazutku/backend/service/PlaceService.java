package com.codexist.sapazutku.backend.service;

import com.codexist.sapazutku.backend.dto.PlaceDto;
import com.codexist.sapazutku.backend.model.Place;


import java.util.List;

public interface PlaceService {
    List<PlaceDto> getPlaces(double latitude, double longitude, double radius);
}
