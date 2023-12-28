package com.codexist.sapazutku.backend.service;

import com.codexist.sapazutku.backend.config.WebClientConfig;
import com.codexist.sapazutku.backend.dto.DisplayNameDto;
import com.codexist.sapazutku.backend.dto.PlaceDto;
import com.codexist.sapazutku.backend.dto.PlaceResponse;
import com.codexist.sapazutku.backend.model.DisplayName;
import com.codexist.sapazutku.backend.model.Location;
import com.codexist.sapazutku.backend.model.Place;
import com.codexist.sapazutku.backend.model.Request;
import com.codexist.sapazutku.backend.repository.PlaceRepository;
import com.codexist.sapazutku.backend.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;
    private final RequestRepository requestRepository;
    private final WebClientConfig webClientConfig;

    @Autowired
    PlaceServiceImpl(PlaceRepository placeRepository, WebClientConfig webClientConfig, RequestRepository requestRepository) {
        this.placeRepository = placeRepository;
        this.webClientConfig = webClientConfig;
        this.requestRepository = requestRepository;
    }

    @Override
    public List<PlaceDto> getPlaces(double latitude, double longitude, double radius) {


        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setRadius(radius);

        // If the request already in db, get from db
        List<Request> requests = requestRepository.findByLocation(location);
        if (!requests.isEmpty()) {
            Request request = requests.get(0);
            System.out.println("RESPONSE FROM from CACHE");
            return request.getPlaces().stream()
                    .map(place -> {
                        PlaceDto placeDto = new PlaceDto();
                        DisplayNameDto displayNameDto = new DisplayNameDto();
                        displayNameDto.setText(place.getDisplayName().getText());
                        placeDto.setDisplayName(displayNameDto);
                        placeDto.setIconMaskBaseUri(place.getIconMaskBaseUri());
                        placeDto.setIconBackgroundColor(place.getIconBackgroundColor());
                        placeDto.setPrimaryType(place.getPrimaryType());
                        return placeDto;
                    })
                    .toList();
        }

        // If the request not in db, get from api

        WebClient webClient = webClientConfig.webClient();
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
        System.out.println("RESPONSE FROM API");
        // Save to db places and request

        List<Place> placesToSave = places.stream()
                .map(this::convertDtoToPlace)
                .collect(Collectors.toList());


        Request newRequest = new Request();
        newRequest.setLocation(location);
        newRequest.setPlaces(placesToSave);
        placesToSave.forEach(place -> place.setRequest(newRequest));

        try {
            requestRepository.save(newRequest);
        } catch (DataIntegrityViolationException e) {
            System.out.println("DataIntegrityViolationException: " + e.getMessage());
            return Collections.emptyList();
        }

        return places;

    }

    private String buildRequestBody(double latitude, double longitude, double radius) {
        return "{ \"locationRestriction\": { \"circle\": { \"center\": { \"latitude\": "
                + latitude + ", \"longitude\": " + longitude + " }, \"radius\": " + radius + " }}}";
    }

    private Place convertDtoToPlace(PlaceDto placeDto) {
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
}