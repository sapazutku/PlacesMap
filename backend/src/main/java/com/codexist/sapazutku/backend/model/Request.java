package com.codexist.sapazutku.backend.model;

import com.codexist.sapazutku.backend.dto.PlaceDto;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "responses")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "response_id")
    private Long id;

    @Embedded
    private Location location;

    @OneToMany(mappedBy = "response",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Place> places;

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> placesToSave) {
        this.places = placesToSave;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}