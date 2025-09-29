package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        this.employeeService = theEmployeeService;
    }

    // Añadir mapping para /"list"

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // obtener los empleados de la bbdd
        List<Employee> theEmployees = employeeService.findAll();

        // añadir al modelo Spring
        theModel.addAttribute("employees", theEmployees);

        return "/employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // crear los atributos del modelo para vincular los datos del formulario
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "/employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {

        // obtener el empleado del service
        Employee theEmployee = employeeService.findById(theId);

        // establecer el empleado en el modelo para pre-rellenar el form
        theModel.addAttribute("employee", theEmployee);

        // mandarlo al form
        return "employees/employee-form";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId, Model theModel) {

        // eliminar el empleado
       employeeService.deleteById(theId);

        // redirigir a /employees/list
        return "redirect:/employees/list";

    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee")Employee theEmployee) {

        // guardar el empleado
        employeeService.save(theEmployee);

        //usar una redireccion para prevenir envios duplicados
        return "redirect:/employees/list";
    }
}
