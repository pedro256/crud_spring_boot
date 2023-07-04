package com.pedro256.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name="CATEGORY_PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table
public class CategoryProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CATEGORY_NAME")
    private String name;
    @Column(name = "CATEGORY_DESCRIPTION")
    private String description;
}
