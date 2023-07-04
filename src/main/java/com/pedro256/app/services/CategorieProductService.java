package com.pedro256.app.services;

import com.pedro256.app.entity.CategoryProductEntity;
import com.pedro256.app.exceptions.BadRequestException;
import com.pedro256.app.models.SaveCategoryProductModel;
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

    public SaveCategoryProductModel save(SaveCategoryProductModel model){
        CategoryProductEntity cPEtt = new CategoryProductEntity(
                model.getId(),
                model.getName(),
                model.getDescription());

        System.out.println(model.getId());

        var response = catProdRepo.save(cPEtt);

        model.setId(response.getId());

        return model;
    }

    public List<SaveCategoryProductModel> listarTodos(){
        var listEtt = catProdRepo.findAll();

        List<SaveCategoryProductModel> list = listEtt.stream()
                .map(ett -> {
                    SaveCategoryProductModel model = new SaveCategoryProductModel();
                    model.setId(ett.getId());
                    model.setName(ett.getName());
                    model.setDescription(ett.getDescription());
                    return  model;
                }).collect(Collectors.toList());


        return list;

    }
}
