package com.pedro256.app.services;

import com.pedro256.app.entity.CategoryProductEntity;
import com.pedro256.app.entity.ProductEntity;
import com.pedro256.app.exceptions.BadRequestException;
import com.pedro256.app.models.CategoryProductModel;
import com.pedro256.app.models.ProductModel;
import com.pedro256.app.repositories.CategoryProductrepository;
import com.pedro256.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryProductrepository categoryProductrepository;

    public Long create(ProductModel product){
        ProductEntity ett = new ProductEntity();
        ett.setName(product.getName());
        ett.setPrice(product.getPrice());
        ett.setBarcode(product.getBarcode());
        if(!(product.getCategoryId() > 0)){
            throw new BadRequestException("Categoria deve ser informado !");
        }
        boolean existsCategoryWithId = categoryProductrepository.existsById(product.getCategoryId());
        if(!existsCategoryWithId){
            throw new BadRequestException("Categoria informada não existe na base de dados !");
        }
        ett.setCategoryId(product.getCategoryId());

        productRepository.save(ett);

        return  ett.getId();
    }

    public boolean update(ProductModel product){

        Optional<ProductEntity> opEntity = productRepository.findById(product.getId());
        if(opEntity.isEmpty()){
            throw new BadRequestException("Produto não encontrado !");
        }
        ProductEntity ett = opEntity.get();

        if(ett.getPrice() != product.getPrice() && product.getPrice() > 0){
            ett.setPrice(product.getPrice());
        }
        if(ett.getName() != product.getName() && product.getName().length()>0){
            ett.setName(product.getName());
        }

        if(ett.getBarcode() != product.getBarcode() && product.getBarcode() != null){
            if(validBarCodeProduct(product.getBarcode())){
                throw new BadRequestException("Codigo de barra incorreto !");
            }
            ett.setBarcode(product.getBarcode());
        }


        if((product.getCategoryId()!=null && product.getCategoryId()>0 )){
            boolean existsCategoryWithId = categoryProductrepository.existsById(product.getCategoryId());
            if(!existsCategoryWithId){
                throw new BadRequestException("Categoria informada não existe na base de dados !");
            }
            ett.setCategoryId(product.getCategoryId());
        }

        productRepository.save(ett);


        return  true;
    }

    public boolean validBarCodeProduct(String barcode){
        if(barcode.length()!=8){
            return  false;
        }
        if(barcode.matches("\\d+")){
            //NÃO CONTEM APENAS NUMEROS
            return  false;
        }
        return  true;
    }
    public List<ProductModel> listarTodos(){
        var listEtt = productRepository.findAll();

        List<ProductModel> list = listEtt.stream()
                .map(ett -> {
                    ProductModel model = new ProductModel();
                    model.setId(ett.getId());
                    model.setName(ett.getName());
                    model.setBarcode(ett.getBarcode());
                    model.setPrice(ett.getPrice());
                    model.setCategoryId(ett.getCategoryId());
                    return  model;
                }).collect(Collectors.toList());


        return list;

    }
}
