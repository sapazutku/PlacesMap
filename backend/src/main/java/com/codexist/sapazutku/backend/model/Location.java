package com.codexist.sapazutku.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Location {
    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;
}
