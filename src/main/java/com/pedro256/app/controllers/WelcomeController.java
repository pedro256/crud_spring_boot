package com.pedro256.app.controllers;

import com.pedro256.app.exceptions.BadRequestException;
import com.pedro256.app.models.base.WelcomeVM;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @GetMapping("/say")
    public WelcomeVM sayWelcome(@RequestParam(value = "name",defaultValue = "User")String name){

        if(name.length()<4){
            throw  new BadRequestException("Name deve conter mais de 3 caracteres");
        }
        return  new WelcomeVM(1,name);
    }
}
