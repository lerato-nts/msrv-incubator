package za.co.msrv.incubator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import za.co.msrv.incubator.exceptions.ExternalAPIException;

@Slf4j
@Service
public class ExternalApiService {
    private final RestTemplate restTemplate;
    private final HttpEntity<String> apiEntity;

    public ExternalApiService(RestTemplateBuilder restTemplateBuilder, HttpEntity<String> apiEntity) {
        this.restTemplate = restTemplateBuilder.build();
        this.apiEntity = apiEntity;
    }

    public String callExternalAPI(String url) {
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
