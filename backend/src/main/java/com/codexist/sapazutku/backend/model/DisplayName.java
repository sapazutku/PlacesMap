package com.codexist.sapazutku.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DisplayName {
    @Column(name = "text")
    private String text;
    @Column(name = "language_code")
    private String languageCode;


    public DisplayName() {
    }

    public DisplayName(String text, String languageCode) {
        this.text = text;
        this.languageCode = languageCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}

