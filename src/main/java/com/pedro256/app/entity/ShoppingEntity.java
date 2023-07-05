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
@Table(name="SHOPPING")
public class ShoppingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CLIENT_NAME")
    private String clientName;

    @Column(name = "CLIENT_CPF")
    private String clientCPF;

    @Column(name = "SELLER_ID")
    private Long sellerId;

    @Column(name="STATUS")
    private int status;


}
