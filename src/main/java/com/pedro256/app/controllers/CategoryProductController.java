package com.pedro256.app.controllers;

import com.pedro256.app.models.model.CategoryProductModel;
import com.pedro256.app.models.base.CreatedResponseModel;
import com.pedro256.app.services.CategorieProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/category-product")
public class CategoryProductController {

    @Autowired
    private CategorieProductService categorieProductService;

    @RequestMapping()
    public List<CategoryProductModel> getAll(){
        return categorieProductService.listarTodos();
    }
    @PostMapping()
    public ResponseEntity<CreatedResponseModel> create(@RequestBody @Valid CategoryProductModel model){
        Long id = categorieProductService.create(model);
        return new ResponseEntity<>(
                new CreatedResponseModel(new Date(),true,"/category-product/"+id),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid CategoryProductModel model){
        model.setId(id);
        categorieProductService.update(model);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categorieProductService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }


}
