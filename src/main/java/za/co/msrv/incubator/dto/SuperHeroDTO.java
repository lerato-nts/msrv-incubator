package za.co.msrv.incubator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;

@Builder
public class SuperHeroDTO implements Serializable {
    @JsonProperty
    private String title;
    @JsonProperty
    private String paragraphText;
    @JsonProperty
    private String imageUrl;
}
