package org.open.covid19.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Country {
    @JsonIgnore
    private Integer id = 0;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Slug")
    private String slug;
    @JsonProperty("ISO2")
    private String iso2;
}
