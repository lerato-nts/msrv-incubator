package za.co.msrv.incubator.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import za.co.msrv.incubator.dto.SuperHeroDTO;

import java.util.List;
import java.util.concurrent.Future;

@Service
public interface IHeroesAsyncService {
    @Async
    Future<List<SuperHeroDTO>> getAsyncSuperHeroList();
    @Async
    Future<SuperHeroDTO> getAsyncSuperHeroByFilter(String searchPhrase);
}
