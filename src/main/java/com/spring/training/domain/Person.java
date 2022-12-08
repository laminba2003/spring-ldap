package com.spring.training.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {
    @NotNull
    String name;
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String country;
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String company;
}
