package com.pedro256.app.controllers;

import com.pedro256.app.models.WelcomeVM;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @GetMapping("/say")
    public WelcomeVM sayWelcome(@RequestParam(value = "name",defaultValue = "User")String name){
        return  new WelcomeVM(1,name);
    }
}
