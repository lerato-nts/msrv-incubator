package za.co.msrv.incubator.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SuperHeroesService {

    private final static String API_URL = "https://superhero-search.p.rapidapi.com/api/heroes";
    private final static String API_HEADER_KEY = "X-RapidAPI-Key";
    private final static String API_HEADER_KEY_VALUE = "5ce0216210msh4bdfcf6de5e3d81p188f53jsne19902040d6a";
    private final static String API_HEADER_HOST = "X-RapidAPI-Host";
    private final static String API_HEADER_HOST_VALUE = "superhero-search.p.rapidapi.com";
    private final RestTemplate restTemplate;

    public SuperHeroesService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getSuperHeroList() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(API_HEADER_KEY, API_HEADER_KEY_VALUE);
        headers.set(API_HEADER_HOST, API_HEADER_HOST_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }
}