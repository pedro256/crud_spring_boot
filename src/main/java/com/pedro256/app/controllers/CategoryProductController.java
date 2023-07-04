package com.pedro256.app.controllers;

import com.pedro256.app.exceptions.BadRequestException;
import com.pedro256.app.models.SaveCategoryProductModel;
import com.pedro256.app.services.CategorieProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category-product")
public class CategoryProductController {

    @Autowired
    private CategorieProductService categorieProductService;

    @RequestMapping()
    public List<SaveCategoryProductModel> getAll(){
        return categorieProductService.listarTodos();
    }
    @PostMapping()
    public SaveCategoryProductModel create(@RequestBody SaveCategoryProductModel model){
        if(model.getId()!= null){
            model.setId(null);
        }
        return categorieProductService.save(model);
    }
    @PutMapping("/{id}")
    public SaveCategoryProductModel update(@PathVariable Long id,@RequestBody SaveCategoryProductModel model){
        model.setId(id);
        return categorieProductService.save(model);
    }


}
