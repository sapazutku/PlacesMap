package com.codexist.sapazutku.backend.dto;

import java.util.List;

public class PlaceResponse {
    private List<PlaceDto> places;

    public PlaceResponse() {
    }

    public PlaceResponse(List<PlaceDto> places) {
           this.places = places;
    }

    public List<PlaceDto> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceDto> places) {
        this.places = places;
    }

}