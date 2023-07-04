package com.pedro256.app.repositories;

import com.pedro256.app.entity.CategoryProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryProductrepository extends JpaRepository<CategoryProductEntity,Long> {
}
