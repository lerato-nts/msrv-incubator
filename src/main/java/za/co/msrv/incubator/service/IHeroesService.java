package za.co.msrv.incubator.service;

import org.springframework.stereotype.Service;
import za.co.msrv.incubator.model.SuperHero;

import java.util.List;

@Service
public interface IHeroesService {
    List<SuperHero> getSuperHeroList();
    List<SuperHero> getSuperHeroByFilter(String searchPhrase, int resultSize);
}
