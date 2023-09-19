package za.co.msrv.incubator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.msrv.incubator.dto.SuperHeroDTO;
import za.co.msrv.incubator.service.IHeroesAsyncService;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@Api(value = "Super Heroes Controller")
@RequestMapping("/api/v1/heroes")
public class SuperHeroesController {
    private final IHeroesAsyncService superHeroesService;

    @Autowired
    public SuperHeroesController(@Qualifier("superHeroesAsyncService") IHeroesAsyncService superHeroesService) {
        this.superHeroesService = superHeroesService;
    }

    @GetMapping
    @ApiOperation(value = "A list of 20 random Super Heroes")
    public ResponseEntity<List<SuperHeroDTO>> getSuperHeroes() throws ExecutionException, InterruptedException {
        Future<List<SuperHeroDTO>> futureSuperHeroDTOList = superHeroesService.getAsyncSuperHeroList();
        List<SuperHeroDTO> superHeroDTOList = futureSuperHeroDTOList.get();

        if(superHeroDTOList.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(superHeroDTOList);
    }

    @GetMapping("/search")
    @ApiOperation(value = "A list of Super Heroes matching the search parameter")
    public ResponseEntity<SuperHeroDTO> getHeroesByFilter(@RequestParam("searchPhrase") String searchPhrase) throws ExecutionException, InterruptedException {
        Future<SuperHeroDTO> futureSuperHeroDto = superHeroesService.getAsyncSuperHeroByFilter(searchPhrase);
        SuperHeroDTO superHeroDto = futureSuperHeroDto.get();

        return ResponseEntity.ok(superHeroDto);
    }
}