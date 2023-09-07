package za.co.msrv.incubator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.msrv.incubator.dto.SuperHeroDTO;
import za.co.msrv.incubator.model.SuperHero;
import za.co.msrv.incubator.service.SuperHeroMapper;
import za.co.msrv.incubator.service.SuperHeroesService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "Super Heroes Controller")
@RequestMapping("/api/v1/heroes")
public class SuperHeroesController {
    private final SuperHeroesService superHeroesService;
    private final SuperHeroMapper superHeroMapper;

    @Autowired
    public SuperHeroesController(SuperHeroesService superHeroesService, SuperHeroMapper superHeroMapper) {
        this.superHeroesService = superHeroesService;
        this.superHeroMapper = superHeroMapper;
    }

    @GetMapping
    @ApiOperation(value = "A list of 20 random Super Heroes")
    public ResponseEntity<List<SuperHeroDTO>> getSuperHeroes() throws JsonProcessingException {
        List<SuperHero> superHeroList = superHeroesService.getSuperHeroList();

        List<SuperHeroDTO> superHeroDTOList = superHeroList.stream()
                .map(superHeroMapper::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(superHeroDTOList);
    }
}