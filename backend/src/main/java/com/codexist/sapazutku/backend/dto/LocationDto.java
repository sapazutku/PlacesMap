package com.codexist.sapazutku.backend.dto;

import com.codexist.sapazutku.backend.model.Location;

public class LocationDto {
    private double latitude;
    private double longitude;
    private double radius;

    public LocationDto() {
    }

    public LocationDto(double latitude, double longitude, double radius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public Location convertToLocation() {
        return new Location(latitude, longitude, radius);
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
