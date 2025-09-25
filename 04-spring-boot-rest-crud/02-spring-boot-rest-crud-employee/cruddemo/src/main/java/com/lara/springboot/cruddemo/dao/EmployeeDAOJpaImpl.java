package com.lara.springboot.cruddemo.dao;

import com.lara.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    // definir campo para entitymanager
    private EntityManager entityManager;

    // crear constructor inyeccion
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        //crear una query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        //ejecutar la query y obtener la lista de resultados
        List<Employee> employees = theQuery.getResultList();

        // devolver los resultados
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        // obtener empleado
        Employee theEmployee = entityManager.find(Employee.class, theId);

        //devolver empleado
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {

        // guardar el empleado
        Employee dbEmployee = entityManager.merge(theEmployee);

        // devolver dbEmployee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {

        // buscar empleado por id
        Employee theEmployee = entityManager.find(Employee.class, theId);

        // eliminar empleado
        entityManager.remove(theEmployee);
    }
}
