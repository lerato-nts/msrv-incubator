package za.co.msrv.incubator.service;

import org.springframework.stereotype.Service;
import za.co.msrv.incubator.dto.SuperHeroDTO;
import za.co.msrv.incubator.model.SuperHero;

@Service
public class SuperHeroMapper implements IHeroMapper {
    public SuperHeroDTO convertToDto(SuperHero superHero) {
        return SuperHeroDTO.builder()
                            .title(superHero.getName())
                            .paragraphText(superHero.getBiography().toString())
                            .imageUrl(superHero.getImages().getMd())
                                    .build();
    }
}
