package com.pedro256.app.services;

import com.pedro256.app.entity.CategoryProductEntity;
import com.pedro256.app.exceptions.BadRequestException;
import com.pedro256.app.exceptions.NotFoundException;
import com.pedro256.app.models.model.CategoryProductModel;
import com.pedro256.app.repositories.CategoryProductrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategorieProductService {
    @Autowired
    private CategoryProductrepository catProdRepo;

    public Long create(CategoryProductModel model){
        CategoryProductEntity cPEtt = new CategoryProductEntity(
                model.getId(),
                model.getName(),
                model.getDescription());


        var response = catProdRepo.save(cPEtt);


        return response.getId();
    }
    public boolean update(CategoryProductModel model){

        Optional<CategoryProductEntity> categoryProductEntity = catProdRepo.findById(model.getId());

        if(categoryProductEntity.isEmpty()){
            throw new NotFoundException("Categoria não encontrada !");
        }

        CategoryProductEntity categoryProduct = categoryProductEntity.get();

        categoryProduct.setName(model.getName());
        categoryProduct.setDescription(model.getDescription());

        var response = catProdRepo.save(categoryProduct);

        return true;
    }

    public List<CategoryProductModel> listarTodos(){
        var listEtt = catProdRepo.findAll();

        List<CategoryProductModel> list = listEtt.stream()
                .map(ett -> {
                    CategoryProductModel model = new CategoryProductModel();
                    model.setId(ett.getId());
                    model.setName(ett.getName());
                    model.setDescription(ett.getDescription());
                    return  model;
                }).collect(Collectors.toList());


        return list;

    }

    public boolean deleteOne(Long id){
        Optional<CategoryProductEntity> ett = catProdRepo.findById(id) ;
        if(ett.isEmpty()){
            throw new NotFoundException("Categoria não encontrada !");
        }
        catProdRepo.delete(ett.get());
        return  true;
    }
}
