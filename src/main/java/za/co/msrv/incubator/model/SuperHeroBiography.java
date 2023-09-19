package za.co.msrv.incubator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuperHeroBiography implements Serializable {
    private String fullName;
    private String alterEgos;
    private String[] aliases;
    private String placeOfBirth;
    private String firstAppearance;
    private String alignment;

    public String getAliases() {
        if(aliases==null || aliases.length<1)
            return "";

        StringBuilder strAliases = new StringBuilder("also known as (");
        String alias = aliases[0];
        strAliases.append(alias);

        for(int i=1; i<aliases.length; i++) {
            alias = aliases[i];
            strAliases.append(", ");
            strAliases.append(alias);
        }

        strAliases.append(")");

        return strAliases.toString();
    }

    public String toString() {
        return  fullName + " " +
                getAliases() + " " +
                "was born at " + placeOfBirth + ", " +
                "and first appeared on " + firstAppearance + ". " +
                "Alter Egos: " + alterEgos + ". " +
                "Aligned to the " + alignment + " side.";
    }
}
