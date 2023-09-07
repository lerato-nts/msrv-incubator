package za.co.msrv.incubator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.msrv.incubator.service.SuperHeroesService;

@RestController
@Api(value = "Super Heroes Controller")
@RequestMapping("/api/v1/heroes")
public class SuperHeroesController {
    private final SuperHeroesService superHeroesService;

    public SuperHeroesController(SuperHeroesService superHeroesService) {
        this.superHeroesService = superHeroesService;
    }

    @GetMapping
    @ApiOperation(value = "A list of 20 random Super Heroes")
    public ResponseEntity<String> getSuperHeroes() {
        String response = superHeroesService.getSuperHeroList();
        return ResponseEntity.ok(response);
    }
}