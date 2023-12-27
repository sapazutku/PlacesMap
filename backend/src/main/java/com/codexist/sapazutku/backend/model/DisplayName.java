package com.codexist.sapazutku.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DisplayName {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "language_code")
    private String languageCode;

}

