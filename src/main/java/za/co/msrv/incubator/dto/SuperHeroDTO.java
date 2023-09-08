package za.co.msrv.incubator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class SuperHeroDTO {
    @JsonProperty
    private String title;
    @JsonProperty
    private String paragraphText;
    @JsonProperty
    private String imageUrl;
}
