package com.pedro256.app.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductModel {

    private Long id;
    private String name;
    private float price;
    private String barcode;
    private Long categoryId;
}
