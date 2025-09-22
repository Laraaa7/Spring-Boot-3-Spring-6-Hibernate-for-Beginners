package com.lara.springboot.demo.demoapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    //"/" que va a devolver "Hello World"

    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

}
