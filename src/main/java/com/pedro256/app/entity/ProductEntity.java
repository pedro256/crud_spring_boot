package com.pedro256.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="PRODUCT")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private float price;
    @Column(name="STOCK")
    private int stock;
    @Column(name = "BARCODE")
    private String barcode;
    @Column(name = "CATEGORY_ID")
    private Long categoryId;


}
