package com.pedro256.app.models.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProductModel {

    private Long id;
    private String name;
    private float price;
    private String barcode;
    private Long categoryId;
}
