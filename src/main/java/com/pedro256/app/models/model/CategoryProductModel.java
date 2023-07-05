package com.pedro256.app.models.model;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
public class CategoryProductModel {
    private Long id;
    @NotBlank
    private String name;
    private String description;


}
