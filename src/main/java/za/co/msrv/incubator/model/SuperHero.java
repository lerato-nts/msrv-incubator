package za.co.msrv.incubator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperHero {
    private String name;
    private SuperHeroBiography biography;
    private SuperHeroImages images;
}
