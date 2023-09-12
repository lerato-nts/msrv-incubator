package za.co.msrv.incubator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import za.co.msrv.incubator.model.SuperHero;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuperHeroesService implements IHeroesService {
    @Value("${api.url}")
    private String API_URL;

    private final RestTemplate restTemplate;
    private final HttpEntity<String> apiEntity;

    public SuperHeroesService(RestTemplateBuilder restTemplateBuilder, HttpEntity<String> apiEntity) {
        this.restTemplate = restTemplateBuilder.build();
        this.apiEntity = apiEntity;
    }

    @Override
    public List<SuperHero> getSuperHeroByFilter(String searchPhrase, int resultSize) {
        try {
            String url = API_URL + "?hero=" + searchPhrase;
            ResponseEntity<String> response = restTemplate
                                                    .exchange(url, HttpMethod.GET, apiEntity, String.class);

            String responseBody = response.getBody();

            if(responseBody==null || responseBody.isEmpty())
                throw new ExternalAPIException("No data found!");

            ObjectMapper objectMapper = new ObjectMapper();
            List<SuperHero> superHeroList = objectMapper.readValue(responseBody, new TypeReference<>() {});

            return superHeroList
                        .stream()
                        .limit(resultSize)
                        .collect(Collectors.toList());
        }
        catch (RestClientException | JsonProcessingException e) {
            String errorMsg = "External API Call Exception: " + e.getMessage();
            throw new ExternalAPIException(errorMsg);
        }
    }
}