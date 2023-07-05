package com.pedro256.app.services;

import com.pedro256.app.entity.ProductEntity;
import com.pedro256.app.entity.ProductShoppingEntity;
import com.pedro256.app.entity.ShoppingEntity;
import com.pedro256.app.enums.ShoppingStatus;
import com.pedro256.app.exceptions.BadRequestException;
import com.pedro256.app.exceptions.NotFoundException;
import com.pedro256.app.models.model.RequestProductsToBuy;
import com.pedro256.app.models.model.ShoppingModel;
import com.pedro256.app.repositories.ProductRepository;
import com.pedro256.app.repositories.ProductShoppingRepository;
import com.pedro256.app.repositories.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingService {

    @Autowired
    private ShoppingRepository shoppingRepository;
    @Autowired
    private ProductShoppingRepository productShoppingRepository;

    @Autowired
    private ProductRepository productRepository;


    public Long create(ShoppingModel shoppingModel){
        ShoppingEntity shopping = new ShoppingEntity();

        shopping.setClientCPF(shoppingModel.getClientCPF());
        shopping.setClientName(shoppingModel.getClientName());
        shopping.setSellerId(shoppingModel.getSellerId());
        shopping.setStatus(ShoppingStatus.INICIADO.getValor());

        shoppingRepository.save(shopping);

        return shopping.getId();
    }


    private void handleIfIsFirstInsert(ShoppingEntity shopping){
        if(shopping.getStatus() == ShoppingStatus.INICIADO.getValor()){
            shopping.setStatus(ShoppingStatus.EM_PROCESSO.getValor());
            shoppingRepository.save(shopping);
        }
    }

    @Transactional
    public Long appendProductsToBuy(Long idShopping, List<RequestProductsToBuy> prodsToBuy){
        Optional<ShoppingEntity> opShopping = shoppingRepository.findById(idShopping);
        if(opShopping.isEmpty()){
            throw new BadRequestException("Shopping não encontrado !!");
        }
        ShoppingEntity shopping = opShopping.get();


        List<ProductEntity> productEntities = new ArrayList<>();
        List<ProductShoppingEntity> productShoppingEntities = new ArrayList<>();

        prodsToBuy.stream().forEach((product)->{
            Optional<ProductEntity> opProd = productRepository.findById(product.getIdProduct());
            if(opProd.isEmpty()){
                throw new NotFoundException("produto da lista não encontrado !");
            }
            ProductEntity prod = opProd.get();


            if(prod.getStock()==0 || (prod.getStock()-product.getQtd())<1){
                throw new NotFoundException("estoque insuficiente !");
            }
            prod.setStock(prod.getStock()-product.getQtd());
            productEntities.add(prod);

            ProductShoppingEntity prodShoppingRelationEntity = new ProductShoppingEntity();
            List<ProductShoppingEntity> productShoppingExisting = productShoppingRepository
                    .findByidShoppingAndidProduct(idShopping,prod.getId());

            if(productShoppingExisting.isEmpty()){
                prodShoppingRelationEntity.setIdShopping(idShopping);
                prodShoppingRelationEntity.setIdProduct(prod.getId());
            }else{
                prodShoppingRelationEntity = productShoppingExisting.get(0);
            }

            if(prodShoppingRelationEntity.getQttd()==null){
                prodShoppingRelationEntity.setQttd(0l);
            }
            prodShoppingRelationEntity.setQttd(
                    prodShoppingRelationEntity.getQttd()+product.getQtd()
            );

            productShoppingEntities.add(prodShoppingRelationEntity);
        });


        handleIfIsFirstInsert(shopping);
        productRepository.saveAll(productEntities);
        productShoppingRepository.saveAll(productShoppingEntities);

        return  idShopping;


    }

    public Long removeProductsToBuy(Long idShopping, List<RequestProductsToBuy> prodsToBuy){

    }

    public Long finishShopping(Long idShopping){

    }
}
