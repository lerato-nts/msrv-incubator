package za.co.discovery.vitality.incubator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public String successMessage() {
        return "Hello World!!!";
    }
}