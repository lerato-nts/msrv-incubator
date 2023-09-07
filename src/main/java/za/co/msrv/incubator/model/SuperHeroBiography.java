package za.co.msrv.incubator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@NoArgsConstructor
//@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperHeroBiography {
    private String fullName;
    private String alterEgos;
    private String[] aliases;
    private String placeOfBirth;
    private String firstAppearance;

    public String getAliases() {
        if(aliases==null || aliases.length<1)
            return "";

        StringBuilder strAliases = new StringBuilder("also known as (");
        for(String alias : aliases) {
            strAliases.append(alias);
            strAliases.append(", ");
        }

        strAliases.append("...)");

        return strAliases.toString();
    }

    public String toString() {
        return  fullName + " " +
                getAliases() + " " +
                "was born at " + placeOfBirth + " " +
                "and first appeared on " + firstAppearance + ". " +
                "Alter Egos: " + alterEgos;
    }
}
