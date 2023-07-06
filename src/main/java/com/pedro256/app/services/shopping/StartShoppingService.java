package com.pedro256.app.services.shopping;

import com.pedro256.app.entity.ShoppingEntity;
import com.pedro256.app.enums.ShoppingStatus;
import com.pedro256.app.models.model.ShoppingModel;
import com.pedro256.app.repositories.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartShoppingService {

    @Autowired
    private ShoppingRepository shoppingRepository;

    public Long execute(ShoppingModel shoppingModel){
        ShoppingEntity shopping = new ShoppingEntity();

        shopping.setClientCPF(shoppingModel.getClientCPF());
        shopping.setClientName(shoppingModel.getClientName());
        shopping.setSellerId(shoppingModel.getSellerId());
        shopping.setStatus(ShoppingStatus.INICIADO.getValor());

        shoppingRepository.save(shopping);

        return shopping.getId();
    }

}
