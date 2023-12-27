package com.codexist.sapazutku.backend.dto;

import com.codexist.sapazutku.backend.model.DisplayName;
import com.codexist.sapazutku.backend.model.Location;
import com.codexist.sapazutku.backend.model.Place;

import javax.xml.stream.XMLStreamReader;

public class PlaceDto {
    private LocationDto location;
    private String iconMaskBaseUri;
    private String iconBackgroundColor;
    private DisplayNameDto displayName;
    private String primaryType;

    public Place convertToEntity(PlaceDto placeDto) {
        Place place = new Place();
        Location location = new Location();

        location.setLatitude(placeDto.getLocation().getLatitude());
        location.setLongitude(placeDto.getLocation().getLongitude());
        place.setLocation(location);

        place.setIconMaskBaseUri(placeDto.getIconMaskBaseUri());
        place.setIconBackgroundColor(placeDto.getIconBackgroundColor());

        DisplayName displayName = new DisplayName();
        displayName.setText(placeDto.getDisplayName().getText());

        place.setDisplayName(displayName.getText());

        place.setPrimaryType(placeDto.getPrimaryType());

        return place;
    }



    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
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

    public DisplayNameDto getDisplayName() {
        return displayName;
    }

    public void setDisplayName(DisplayNameDto displayName) {
        this.displayName = displayName;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
    }
}
