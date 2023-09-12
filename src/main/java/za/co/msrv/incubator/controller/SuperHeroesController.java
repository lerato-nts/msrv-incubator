package za.co.msrv.incubator.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.msrv.incubator.dto.SuperHeroDTO;
import za.co.msrv.incubator.model.SuperHero;
import za.co.msrv.incubator.service.IHeroesService;
import za.co.msrv.incubator.service.SuperHeroMapper;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "Super Heroes Controller")
@RequestMapping("/api/v1/heroes")
public class SuperHeroesController {
    private final IHeroesService superHeroesService;
    private final SuperHeroMapper superHeroMapper;

    @Autowired
    public SuperHeroesController(IHeroesService superHeroesService, SuperHeroMapper superHeroMapper) {
        this.superHeroesService = superHeroesService;
        this.superHeroMapper = superHeroMapper;
    }

    @GetMapping
    @ApiOperation(value = "A list of 20 random Super Heroes")
    public ResponseEntity<List<SuperHeroDTO>> getSuperHeroes() {
        List<SuperHero> superHeroList = superHeroesService.getSuperHeroList();

        return mapEntityToDTO(superHeroList);
    }

    @GetMapping("/search")
    @ApiOperation(value = "A list of Super Heroes matching the search parameter")
    public ResponseEntity<List<SuperHeroDTO>> getHeroesByFilter(
            @RequestParam("searchPhrase") String searchPhrase,
            @RequestParam(defaultValue = "5", name = "resultSize")
            @Min(message = "The smallest value allowed is 1 (One)", value = 1)
            @Max(message = "The highest value allowed is 20 (Twenty)", value = 20)
            Integer resultSize)
    {
        List<SuperHero> superHeroList = superHeroesService.getSuperHeroByFilter(searchPhrase, resultSize);

        return mapEntityToDTO(superHeroList);
    }

    private ResponseEntity<List<SuperHeroDTO>> mapEntityToDTO(List<SuperHero> superHeroList) {
        if(superHeroList.isEmpty())
            return ResponseEntity.notFound().build();

        List<SuperHeroDTO> superHeroDTOList = superHeroList.stream()
                .map(superHeroMapper::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(superHeroDTOList);
    }
}