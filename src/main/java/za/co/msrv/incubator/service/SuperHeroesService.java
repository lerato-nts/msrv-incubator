package za.co.msrv.incubator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import za.co.msrv.incubator.model.SuperHero;

import java.util.List;


@Slf4j
@Service
public class SuperHeroesService implements IHeroesService {
    private static final String API_PARAM = "heroes";
    private static final String API_FILTER = "?hero=";
    @Value("${api.url}")
    private String API_URL;

    private final RestTemplate restTemplate;
    private final HttpEntity<String> apiEntity;

    public SuperHeroesService(RestTemplateBuilder restTemplateBuilder, HttpEntity<String> apiEntity) {
        this.restTemplate = restTemplateBuilder.build();
        this.apiEntity = apiEntity;
    }

    @Override
    @Cacheable("heroes")
    public List<SuperHero> getSuperHeroList() {
        try {
            log.info("Get a random list of Super Heroes");

            String url = API_URL + API_PARAM;
            ObjectMapper objectMapper = new ObjectMapper();
            String apiResults = callExternalAPI(url);

            return objectMapper.readValue(apiResults, new TypeReference<>() {});
        }
        catch (RestClientException | JsonProcessingException e) {
            throw new ExternalAPIException(e.getMessage());
        }
    }

    @Override
    @Cacheable(value = "hero", key = "#searchPhrase")
    public SuperHero getSuperHeroByFilter(String searchPhrase) {
        String apiResults = "";
        try {
            log.info("Get Super hero by Filter");

            String url = API_URL + API_FILTER + searchPhrase;
            ObjectMapper objectMapper = new ObjectMapper();

            apiResults = callExternalAPI(url);

            return objectMapper
                    .readValue(apiResults, new TypeReference<>() {});
        }
        catch (RestClientException e) {
            log.warn("Rest client error: {}", e.getMessage());
            throw new ExternalAPIException(e.getMessage());
        }
        catch (JsonProcessingException e) {
            log.warn("Error converting data from external API to an object.");
            throw new ExternalAPIException(apiResults);
        }
    }

    private String callExternalAPI(String url) {
        log.info("Calling the External API: {}", url);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, apiEntity, String.class);
        String responseBody = response.getBody();

        if (responseBody == null || responseBody.isEmpty()) {
            log.warn("No data exception thrown!");
            throw new ExternalAPIException("No data found!");
        }

        log.trace("Response from API: {} ", responseBody);
        return responseBody;
    }
}