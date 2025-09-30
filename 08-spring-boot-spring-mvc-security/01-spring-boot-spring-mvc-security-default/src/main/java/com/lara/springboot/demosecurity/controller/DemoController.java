package com.lara.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String showHome(){
        return "home";
    }

    // Mapping para /leaders
    @GetMapping("/leaders")
    public String showLeaders(){
        return "leaders";
    }

    // Mapping para /systems
    @GetMapping("/systems")
    public String showSystems(){
        return "systems";
    }


}
