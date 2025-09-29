package com.lara.springboot.thymeleafdemo.controller;

import com.lara.springboot.thymeleafdemo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @Value("${systems}")
    private List<String> systems;

    @GetMapping("/showStudentForm")
    public String showForm(Model theModel){

        // crear objeto student
        Student theStudent = new Student();

        //añadir el objeto al modelo
        theModel.addAttribute("student",theStudent);

        // añadir la lista de paises al modelo
        theModel.addAttribute("countries",countries);

        // añadir la lista de lenguajes al modelo
        theModel.addAttribute("languages",languages);

        // añadir la lista de sistemas al modelo
        theModel.addAttribute("systems",systems);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processStudentForm(@ModelAttribute("student") Student theStudent){

        System.out.printf("theStudent: " +theStudent.getFirstName()+" "+theStudent.getLastName());
        return "student-confirmation";
    }
}
