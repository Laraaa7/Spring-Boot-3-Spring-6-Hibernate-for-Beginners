package com.lara.springboot.cruddemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lara.springboot.cruddemo.entity.Employee;
import com.lara.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    private ObjectMapper objectMapper;

    // inyectar el dao del empleado
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService, ObjectMapper theObjectMapper) {
        employeeService = theEmployeeService;
        objectMapper = theObjectMapper;
    }

    // exponer "/employees" y devolver una lista de empleados
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // añadir mapping para GET /employes/{employeeId}

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);

        }
        return theEmployee;
    }

    // añadir mapping para POST /employees - añadir nuevos empleados

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        // por si acaso pasan un id en JSON poner id en 0
        // esto es para forzar el guardado de un nuevo item en vez de actualizar

        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // añadir mapping para PUT /employees - actualizar un empleado existente
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){

        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // añadir mapping para PATCH /employees/{employeeId} - patch employee, actualizacion parcial
    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId,
                                  @RequestBody Map<String, Object> patchPayload){

        Employee tempEmployee = employeeService.findById(employeeId);

        // lanzar excepcion si es null
        if(tempEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        // lanzar excepcions si el body de la peticion contiene la clave "id"
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("Employee id not allowed in request body - " + employeeId);
        }
        Employee patchEmployee = apply(patchPayload, tempEmployee);

        Employee dbEmployee = employeeService.save(patchEmployee);

        return dbEmployee;
    }

    private Employee apply(Map<String, Object> patchPayload, Employee tempEmployee){

        // convertir objeto employee a un ObjectNode JSON
        ObjectNode employeeNode = objectMapper.convertValue(tempEmployee, ObjectNode.class);

        // convertir el patchPayload a un ObjectNode JSON
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        // Mergear las actualizaciones del patch al node de employee
        employeeNode.setAll(patchNode);
        return objectMapper.convertValue(employeeNode, Employee.class);
    }

    // añadir  mapping para DELETE /employees/{employeeId} - borrar employee

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){

        Employee tempEmployee = employeeService.findById(employeeId);

        // lanzar excepcion si es null
        if(tempEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);
        return "Employee id deleted - " + employeeId;
    }

}
