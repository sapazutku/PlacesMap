package com.codexist.sapazutku.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private DisplayName displayName;

    @Column(name = "type", nullable = true)
    private String primaryType;

    @Embedded
    private Location location;

    @Column(name = "icon_uri", nullable = true)
    private String iconMaskBaseUri;

    @Column(name = "icon_color", nullable = true)
    private String iconBackgroundColor;
}
