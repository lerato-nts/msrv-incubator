package za.co.msrv.incubator.service;

import org.springframework.stereotype.Service;
import za.co.msrv.incubator.dto.SuperHeroDTO;

import java.util.List;

@Service
public interface IHeroesService {
    List<SuperHeroDTO> getSuperHeroList();
    SuperHeroDTO getSuperHeroByFilter(String searchPhrase);
}
