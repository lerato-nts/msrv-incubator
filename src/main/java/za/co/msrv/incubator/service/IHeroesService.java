package za.co.msrv.incubator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import za.co.msrv.incubator.model.SuperHero;

import java.util.List;

@Service
public interface IHeroesService {
    public List<SuperHero> getSuperHeroList() throws JsonProcessingException;
}
