package za.co.msrv.incubator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import za.co.msrv.incubator.dto.SuperHeroDTO;
import za.co.msrv.incubator.model.SuperHero;

import java.util.List;

public interface IHeroMapper {
    SuperHeroDTO convertToDto(SuperHero superHero);

    SuperHeroDTO convertStringToDto(String apiResponse) throws JsonProcessingException;

    List<SuperHeroDTO> convertStringToDtoList(String apiResponse) throws JsonProcessingException;
}
