package za.co.msrv.incubator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import za.co.msrv.incubator.model.SuperHero;

import java.util.List;

@Service
public class SuperHeroesService implements IHeroesService {
    @Value("${api.url}")
    private String API_URL;
    @Value("${api.header.key}")
    private String API_HEADER_KEY;
    @Value("${api.header.key.value}")
    private String API_HEADER_KEY_VALUE;
    @Value("${api.header.host}")
    private String API_HEADER_HOST;
    @Value("${api.header.host.value}")
    private String API_HEADER_HOST_VALUE;

    private final RestTemplate restTemplate;

    public SuperHeroesService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<SuperHero> getSuperHeroList() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(API_HEADER_KEY, API_HEADER_KEY_VALUE);
            headers.set(API_HEADER_HOST, API_HEADER_HOST_VALUE);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.GET, entity, String.class);

            String responseBody = response.getBody();

            if(responseBody==null || responseBody.isEmpty())
                throw new ExternalAPIException("No data found!");

            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(responseBody, new TypeReference<List<SuperHero>>() {});
        }
        catch (RestClientException | JsonProcessingException e) {
            String errorMsg = "External API Call Exception: " + e.getMessage();
            throw new ExternalAPIException(errorMsg);
        }
    }
}