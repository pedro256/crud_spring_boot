package com.pedro256.app.services.shopping;

import com.pedro256.app.entity.ShoppingEntity;
import com.pedro256.app.enums.ShoppingStatus;
import com.pedro256.app.exceptions.NotFoundException;
import com.pedro256.app.repositories.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinishShoppingService {
    @Autowired
    private ShoppingRepository shoppingRepository;
    public Long execute(Long idShopping){
        Optional<ShoppingEntity> opShopping = shoppingRepository.findById(idShopping);

        if(opShopping.isEmpty()){
            throw new NotFoundException("Shopping não encontrado !");
        }
        ShoppingEntity shopping = opShopping.get();
        shopping.setStatus(ShoppingStatus.FINALIZADO.getValor());

        shoppingRepository.save(shopping);

        return shopping.getId();
    }
}
