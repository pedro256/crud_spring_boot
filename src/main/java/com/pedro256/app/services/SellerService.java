package com.pedro256.app.services;

import com.pedro256.app.entity.SellerEntity;
import com.pedro256.app.exceptions.BadRequestException;
import com.pedro256.app.exceptions.NotFoundException;
import com.pedro256.app.models.model.SellerModel;
import com.pedro256.app.repositories.SellerRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public Long create(SellerModel seller){
        SellerEntity ett = new SellerEntity();

        ett.setName(seller.getName());
        ett.setInterpriseId(seller.getInterpriseId());
        ett.setBirthDate(seller.getBirthDate());

        sellerRepository.save(ett);

        return  ett.getId();
    }
    public boolean update(SellerModel seller){
        Optional<SellerEntity> opEtt = sellerRepository.findById(seller.getId());
        if(opEtt.isEmpty()){
            throw new NotFoundException("Seller não encontrado !");
        }
        SellerEntity sellerEtt = opEtt.get();

        if(seller.getName() != sellerEtt.getName()){
            sellerEtt.setName(seller.getName());
        }
        if(seller.getBirthDate() != sellerEtt.getBirthDate()){
            sellerEtt.setBirthDate(seller.getBirthDate());
        }


        sellerRepository.save(sellerEtt);

        return true;
    }

    public List<SellerModel> listAll(){
        List<SellerEntity> listEtt = sellerRepository.findAll();

        List<SellerModel> list = listEtt.stream().map((item)->{
            SellerModel s = new SellerModel();
            s.setBirthDate(item.getBirthDate());
            s.setId(item.getId());
            s.setInterpriseId(item.getInterpriseId());
            s.setName(item.getName());
            return  s;
        }).collect(Collectors.toList());
        return  list;
    }

    public SellerModel findById(Long id){
       Optional<SellerEntity> finded = sellerRepository.findById(id);
       if(finded.isEmpty()){
           throw new NotFoundException("Seller not found");
       }
       var ett = finded.get();
       return new SellerModel(id,ett.getName(),ett.getInterpriseId(),ett.getBirthDate());
    }

}
