package com.lara.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    // crear un mapping para "/hello"

    @GetMapping("/hello")
    public String sayhello(Model theModel){

        theModel.addAttribute("theDate", java.time.LocalDate.now());

        return "helloworld";
    }
}
