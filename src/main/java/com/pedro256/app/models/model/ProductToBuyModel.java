package com.pedro256.app.models.model;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductToBuyModel {
    @NotNull
    private Long idProduct;
    @NotNull
    private int qtd;
}
