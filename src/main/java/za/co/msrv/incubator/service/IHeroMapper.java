package za.co.msrv.incubator.service;

import za.co.msrv.incubator.dto.SuperHeroDTO;
import za.co.msrv.incubator.model.SuperHero;

public interface IHeroMapper {
    SuperHeroDTO convertToDto(SuperHero superHero);
}
