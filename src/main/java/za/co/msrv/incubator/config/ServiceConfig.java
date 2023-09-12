package za.co.msrv.incubator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

@Configuration
public class ServiceConfig {
    @Value("${api.header.key.name}")
    private String API_HEADER_KEY;
    @Value("${api.header.key.value}")
    private String API_HEADER_KEY_VALUE;
    @Value("${api.header.host.name}")
    private String API_HEADER_HOST;
    @Value("${api.header.host.value}")
    private String API_HEADER_HOST_VALUE;

    @Bean
    public HttpHeaders externalApiHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(API_HEADER_KEY, API_HEADER_KEY_VALUE);
        headers.set(API_HEADER_HOST, API_HEADER_HOST_VALUE);

        return headers;
    }

    @Bean
    public HttpEntity<String> externalApiEntity() {
        return new HttpEntity<>(externalApiHeaders());
    }
}
