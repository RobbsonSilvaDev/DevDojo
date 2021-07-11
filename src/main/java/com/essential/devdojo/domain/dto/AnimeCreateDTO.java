package com.essential.devdojo.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AnimeCreateDTO {

    @NotEmpty(message = "Please, insert the name")
    private String name;

}
