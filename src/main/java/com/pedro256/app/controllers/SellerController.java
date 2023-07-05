package com.pedro256.app.controllers;

import com.pedro256.app.models.base.CreatedResponseModel;
import com.pedro256.app.models.model.SellerModel;
import com.pedro256.app.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping()
    public List<SellerModel> getAll(){
        return  sellerService.listAll();
    }

    @GetMapping("/{id}")
    public SellerModel getAll(@PathVariable Long id){
        return  sellerService.findById(id);
    }

    @PostMapping()
    public ResponseEntity<CreatedResponseModel> create(@RequestBody SellerModel sellerModel){
        Long id =  sellerService.create(sellerModel);

        return new ResponseEntity(
                new CreatedResponseModel(new Date(),true,"/selller/"+id),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreatedResponseModel> update(@PathVariable Long id,@RequestBody SellerModel sellerModel){
        sellerModel.setId(id);
        sellerService.update(sellerModel);
        return ResponseEntity.noContent().build();
    }
}
