package com.pedro256.app.controllers;

import com.pedro256.app.models.CategoryProductModel;
import com.pedro256.app.models.CreatedResponseModel;
import com.pedro256.app.models.ProductModel;
import com.pedro256.app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping()
    public List<ProductModel> getAll(){
        return productService.listarTodos();
    }

    @PostMapping()
    public ResponseEntity<CreatedResponseModel> create(@RequestBody ProductModel model){
        Long id = productService.create(model);
        return new ResponseEntity(
                new CreatedResponseModel(new Date(),true,"/products/"+id),
                HttpStatus.CREATED);
        //return new CreatedResponseModel(new Date(),true,"/products/"+id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody ProductModel model){
        model.setId(id);
        productService.update(model);
        return ResponseEntity.noContent().build();
    }
}
