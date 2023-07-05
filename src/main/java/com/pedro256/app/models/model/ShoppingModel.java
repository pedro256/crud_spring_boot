package com.pedro256.app.models.model;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ShoppingModel {

    private Long id;

    @NotBlank
    private String clientName;
    @CPF
    private String clientCPF;
    @NotNull
    private Long sellerId;
}
