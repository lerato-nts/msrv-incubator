package za.co.msrv.incubator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import za.co.msrv.incubator.dto.SuperHeroDTO;
import za.co.msrv.incubator.model.SuperHero;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuperHeroMapper implements IHeroMapper {

    ObjectMapper objectMapper;

    public SuperHeroMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public SuperHeroDTO convertStringToDto(String apiResponse) throws JsonProcessingException {
        SuperHero superHero = objectMapper.readValue(apiResponse, new TypeReference<>() {});

        return convertToDto(superHero);
    }

    public SuperHeroDTO convertToDto(SuperHero superHero) {
        return SuperHeroDTO.builder()
                .title(superHero.getName())
                .paragraphText(superHero.getBiography().toString())
                .imageUrl(superHero.getImages().getMd())
                .build();
    }

    public List<SuperHeroDTO> convertStringToDtoList(String apiResponse) throws JsonProcessingException {
        List<SuperHero> superHeroList = objectMapper.readValue(apiResponse, new TypeReference<>() {});

        return superHeroList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
