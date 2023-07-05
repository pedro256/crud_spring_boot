package com.pedro256.app.repositories;

import com.pedro256.app.entity.ProductShoppingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductShoppingRepository extends JpaRepository<ProductShoppingEntity,Long> {

    List<ProductShoppingEntity> findByidShoppingAndidProduct(Long idShopping, Long idProduct);
}
