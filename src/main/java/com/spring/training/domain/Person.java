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
    @JsonProperty
    @NotNull
    String uid;
    @NotNull
    String name;
    @NotNull
    String title;
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    String telephone;
    String address;

    public String getLastName() {
        return name.substring(name.lastIndexOf(" ") + 1);
    }
}
