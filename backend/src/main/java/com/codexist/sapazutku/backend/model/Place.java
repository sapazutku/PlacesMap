package com.codexist.sapazutku.backend.model;

import com.codexist.sapazutku.backend.dto.PlaceDto;
import jakarta.persistence.*;

@Entity
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private DisplayName displayName;

    @Column(name = "type")
    private String primaryType;

    @Embedded
    private Location location;

    @Column(name = "icon_uri")
    private String iconMaskBaseUri;

    @Column(name = "icon_color")
    private String iconBackgroundColor;

    public Place() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DisplayName getDisplayName() {
        return displayName;
    }


    public String getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getIconMaskBaseUri() {
        return iconMaskBaseUri;
    }

    public void setIconMaskBaseUri(String iconMaskBaseUri) {
        this.iconMaskBaseUri = iconMaskBaseUri;
    }

    public String getIconBackgroundColor() {
        return iconBackgroundColor;
    }

    public void setIconBackgroundColor(String iconBackgroundColor) {
        this.iconBackgroundColor = iconBackgroundColor;
    }

    public void setDisplayName(String text) {
        this.displayName = new DisplayName(text, "en");
    }
}