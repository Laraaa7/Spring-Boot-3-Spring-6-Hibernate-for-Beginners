package com.lara.cruddemo.dao;

import com.lara.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // definir campo para el entity manager
    private EntityManager entityManager;

    // inyectar entity manager usando inyector de constructor
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implementar metodo save
    @Override
    @Transactional
    public void save(Student theStudent) {
    entityManager.persist(theStudent); // guarda el estudiante en la bbdd
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id); //busca el estudiante por el ID
    }

    @Override
    public List<Student> findAll() {

       // crear query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);

        //devolver resultados de la query
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        // crear query
        TypedQuery<Student> theQuery = entityManager.createQuery(
                                    "FROM Student WHERE lastName = :theData", Student.class);

        // establecer parametros query
        theQuery.setParameter("theData", theLastName);

        //devolver resultados query
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {

        // obtener el estudiante
        Student theStudent = entityManager.find(Student.class, id);

        // eliminar el estudiante
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }
}
