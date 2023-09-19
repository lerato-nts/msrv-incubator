package za.co.msrv.incubator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import za.co.msrv.incubator.dto.SuperHeroDTO;
import za.co.msrv.incubator.exceptions.ExternalAPIException;

import java.util.List;


@Slf4j
@Service
public class SuperHeroesService implements IHeroesService {
    @Value("${api.url}")
    private String API_URL;
    private static final String API_PARAM = "heroes";
    private static final String API_FILTER = "?hero=";

    private final SuperHeroMapper superHeroMapper;
    private final ExternalApiService externalApiService;

    public SuperHeroesService(SuperHeroMapper superHeroMapper, ExternalApiService externalApiService) {
        this.superHeroMapper = superHeroMapper;
        this.externalApiService = externalApiService;
    }

    @Override
    @Cacheable("heroes")
    public List<SuperHeroDTO> getSuperHeroList()
    {
        log.info("Get a random list of Super Heroes");
        try {
            final String url = API_URL + API_PARAM;
            String apiResponse = externalApiService.callExternalAPI(url);
            return superHeroMapper.convertStringToDtoList(apiResponse);
        }
        catch (RestClientException | JsonProcessingException e) {
            log.warn("Rest client error: {}", e.getMessage());
            throw new ExternalAPIException(e.getMessage());
        }
    }

    @Override
    @Cacheable(value = "hero", key = "#searchPhrase")
    public SuperHeroDTO getSuperHeroByFilter(String searchPhrase)
    {
        log.info("Get Super hero by Filter");
        try {
            final String url = API_URL + API_FILTER + searchPhrase;
            String apiResponse = externalApiService.callExternalAPI(url);
            return superHeroMapper.convertStringToDto(apiResponse);
        }
        catch (RestClientException e) {
            log.warn("Rest client error: {}", e.getMessage());
            throw new ExternalAPIException(e.getMessage());
        } catch (JsonProcessingException e) {
            throw new ExternalAPIException(e.getMessage());
        }
    }
}