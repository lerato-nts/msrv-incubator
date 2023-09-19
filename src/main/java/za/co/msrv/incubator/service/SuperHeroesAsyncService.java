package za.co.msrv.incubator.service;

import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import za.co.msrv.incubator.dto.SuperHeroDTO;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class SuperHeroesAsyncService implements IHeroesAsyncService {
    private final IHeroesService superHeroesService;

    public SuperHeroesAsyncService(IHeroesService superHeroesService) {
        this.superHeroesService = superHeroesService;
    }

    @Override
    public Future<List<SuperHeroDTO>> getAsyncSuperHeroList() {
        return new AsyncResult<>(
                superHeroesService.getSuperHeroList()
        );
    }

    @Override
    public Future<SuperHeroDTO> getAsyncSuperHeroByFilter(String searchPhrase) {
        return new AsyncResult<>(
                superHeroesService.getSuperHeroByFilter(searchPhrase)
        );
    }
}
