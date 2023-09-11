package za.co.msrv.incubator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.io.Serializable;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperHero implements Serializable {
    private String name;
    private SuperHeroBiography biography;
    private SuperHeroImages images;
}
