package com.codexist.sapazutku.backend.service;

import com.codexist.sapazutku.backend.config.WebClientConfig;
import com.codexist.sapazutku.backend.dto.PlaceDto;
import com.codexist.sapazutku.backend.dto.PlaceResponse;
import com.codexist.sapazutku.backend.model.Location;
import com.codexist.sapazutku.backend.model.Place;
import com.codexist.sapazutku.backend.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;
    private final WebClientConfig webClientConfig;

    @Autowired
    PlaceServiceImpl(PlaceRepository placeRepository, WebClientConfig webClientConfig) {
        this.placeRepository = placeRepository;
        this.webClientConfig = webClientConfig;
    }

    @Override
    public List<PlaceDto> getPlaces(double latitude, double longitude, double radius) {
        WebClient webClient = webClientConfig.webClient(); // Get the WebClient instance from the config


        Mono<PlaceResponse> placeResponseMono = webClient.post()
                .bodyValue(buildRequestBody(latitude, longitude, radius))
                .retrieve()
                .bodyToMono(PlaceResponse.class);

        PlaceResponse placeResponse = null;
        try{
            placeResponse = placeResponseMono.block();
        }
        catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }

        if (placeResponse == null) {
            return null;
        }
        List<PlaceDto> places = placeResponse.getPlaces();

        List<Place> placesToSave = places.stream()
                .map(placeDto -> {
                    Place place = new Place();
                    Location location = new Location();
                    location.setLatitude(placeDto.getLocation().getLatitude());
                    location.setLongitude(placeDto.getLocation().getLongitude());
                    place.setLocation(location);
                    place.setDisplayName(placeDto.getDisplayName().getText());
                    place.setIconMaskBaseUri(placeDto.getIconMaskBaseUri());
                    place.setIconBackgroundColor(placeDto.getIconBackgroundColor());
                    place.setPrimaryType(placeDto.getPrimaryType());
                    return place;
                })
                .collect(Collectors.toList());

        try {
            placeRepository.saveAll(placesToSave);
        } catch (DataIntegrityViolationException e) {
            System.out.println("DataIntegrityViolationException: " + e.getMessage());
        }
        return places;
    }

    private String buildRequestBody(double latitude, double longitude, double radius) {
        return "{ \"locationRestriction\": { \"circle\": { \"center\": { \"latitude\": "
                + latitude + ", \"longitude\": " + longitude + " }, \"radius\": " + radius + " }}}";
    }
}