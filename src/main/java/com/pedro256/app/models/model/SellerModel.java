package com.pedro256.app.models.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerModel {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String interpriseId;
    @NotNull
    private Date birthDate;
}
