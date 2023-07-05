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
@Table(name="PRODUCT_SHOPPING")
public class ProductShoppingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ID_SHOPPING")
    private Long idShopping;
    @Column(name = "ID_PRODUCT")
    private Long idProduct;
    @Column(name = "QTTD")
    private Long qttd;
}

