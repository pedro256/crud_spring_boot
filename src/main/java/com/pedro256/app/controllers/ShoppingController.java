package com.pedro256.app.controllers;

import com.pedro256.app.models.base.CreatedResponseModel;
import com.pedro256.app.models.model.RequestProductsToBuy;
import com.pedro256.app.models.model.SellerModel;
import com.pedro256.app.models.model.ShoppingModel;
import com.pedro256.app.services.shopping.AppendProductToListService;
import com.pedro256.app.services.shopping.FinishShoppingService;
import com.pedro256.app.services.shopping.RemoveProducToListService;
import com.pedro256.app.services.shopping.StartShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    @Autowired
    AppendProductToListService appendProductToListService;
    @Autowired
    RemoveProducToListService removeProducToListService;
    @Autowired
    StartShoppingService startShoppingService;
    @Autowired
    FinishShoppingService finishShoppingService;

    @PostMapping()
    public ResponseEntity<CreatedResponseModel> create(@RequestBody ShoppingModel shoppingModel){
        Long id =  startShoppingService.execute(shoppingModel);

        return new ResponseEntity(
                new CreatedResponseModel(new Date(),true,id+""),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/finish/{id}")
    public ResponseEntity<Void> finish(@PathVariable Long id){
        finishShoppingService.execute(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/products/append/{idShopping}")
    public  ResponseEntity<Void> appendProducts(@PathVariable Long idShopping, @RequestBody RequestProductsToBuy requestProductsToBuy){
        var lista = requestProductsToBuy.getListProducts();
        appendProductToListService.execute(idShopping,lista);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/products/remove/{idShopping}")
    public  ResponseEntity<Void> removeProducts(@PathVariable Long idShopping, @RequestBody RequestProductsToBuy requestProductsToBuy){
        var lista = requestProductsToBuy.getListProducts();
        removeProducToListService.execute(idShopping,lista);
        return ResponseEntity.noContent().build();
    }
}
