package com.codexist.sapazutku.backend.dto;

public class DisplayNameDto {
    private String text;
    private String languageCode;

    public DisplayNameDto() {
    }

    public DisplayNameDto(String text, String languageCode) {
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
