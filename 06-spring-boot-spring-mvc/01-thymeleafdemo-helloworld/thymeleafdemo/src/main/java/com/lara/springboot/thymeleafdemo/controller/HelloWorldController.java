package com.lara.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    // nuevo metodo controller para mostrar el form inicial HTML

    @GetMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    // metodo controller para procesar el form
    @RequestMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    // metodo controller para ler datos del form
    // y añadir datos al modelo

    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model){

        // leer la peticion del formulario html
        String theName = request.getParameter("studentName");

        // convertir datos a mayuscula
        theName = theName.toUpperCase();

        // crear el mensaje
        String result = "Yo! " + theName;

        // añadir mensaje al modelo
        model.addAttribute("message", result);

        return "helloworld";
    }

    @PostMapping("/processFormVersionThree")
    public String processFormVersionThree(@RequestParam("studentName") String theName, Model model){

        // convertir datos a mayuscula
        theName = theName.toUpperCase();

        // crear el mensaje
        String result = "Hey My Friend from v3! " + theName;

        // añadir mensaje al modelo
        model.addAttribute("message", result);

        return "helloworld";
    }

}
