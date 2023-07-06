package com.pedro256.app.services.shopping;

import com.pedro256.app.entity.ProductEntity;
import com.pedro256.app.entity.ProductShoppingEntity;
import com.pedro256.app.entity.ShoppingEntity;
import com.pedro256.app.exceptions.BadRequestException;
import com.pedro256.app.exceptions.NotFoundException;
import com.pedro256.app.models.model.ProductToBuyModel;
import com.pedro256.app.models.model.RequestProductsToBuy;
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
public class RemoveProducToListService {
    @Autowired
    private ShoppingRepository shoppingRepository;
    @Autowired
    private ProductShoppingRepository productShoppingRepository;

    @Autowired
    private ProductRepository productRepository;

    private ProductEntity handleProductToRemove(ProductToBuyModel product){
        Optional<ProductEntity> opProd = productRepository.findById(product.getIdProduct());
        if(opProd.isEmpty()){
            throw new NotFoundException("produto da lista não encontrado !");
        }
        ProductEntity prod = opProd.get();
        prod.setStock(prod.getStock()+product.getQtd());
        return  prod;
    }
    private ShoppingEntity findShopping(Long idShopping){
        Optional<ShoppingEntity> opShopping = shoppingRepository.findById(idShopping);
        if(opShopping.isEmpty()){
            throw new BadRequestException("Shopping não encontrado !!");
        }
        return opShopping.get();
    }
    private ProductShoppingEntity handleShoppingProdToRemove(Long idShopping,Long idProd,int qtd){

        ProductShoppingEntity pShoppRel = new ProductShoppingEntity();
        List<ProductShoppingEntity> productShoppingExisting = productShoppingRepository
                .findByidShoppingAndIdProduct(idShopping,idProd);

        if(productShoppingExisting.isEmpty()){
            throw new BadRequestException("item não esta nas compras");
        }
        pShoppRel = productShoppingExisting.get(0);

        if(pShoppRel.getQttd()-qtd <0){
            throw new BadRequestException("a quantidade de itens não pode ser removida");
        }
        pShoppRel.setQttd(pShoppRel.getQttd()-qtd);

        return  pShoppRel;
    }



    @Transactional
    public Long execute(Long idShopping, List<ProductToBuyModel> prodsToBuy){

        ShoppingEntity shopping = findShopping(idShopping);
        List<ProductEntity> productEntities = new ArrayList<>();
        List<ProductShoppingEntity> productShoppingEntities = new ArrayList<>();


        prodsToBuy.stream().forEach(product -> {

            var prod = handleProductToRemove(product);
            productEntities.add(prod);

            var pShoppRel = handleShoppingProdToRemove(idShopping,prod.getId(),product.getQtd());
            productShoppingEntities.add(pShoppRel);
        });


        productRepository.saveAll(productEntities);
        productShoppingRepository.saveAll(productShoppingEntities);

        return idShopping;
    }

}
