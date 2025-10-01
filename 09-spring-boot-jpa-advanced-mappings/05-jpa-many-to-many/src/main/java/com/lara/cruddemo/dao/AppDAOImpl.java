package com.lara.cruddemo.dao;

import com.lara.cruddemo.entity.Course;
import com.lara.cruddemo.entity.Instructor;
import com.lara.cruddemo.entity.InstructorDetail;
import com.lara.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    // definir campo para entity manager
    private EntityManager entityManager;

    // inyectar entity manager usando inyeccion de constructor
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);

    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        // recuperar el instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        // obtener los cursos
        List<Course> courses = tempInstructor.getCourses();

        // romper asociacion con todos los cursos para el instructor
        for(Course tempCourse : courses){
            tempCourse.setInstructor(null);
        }

        // borrar el instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        // recuperar el instructor
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // borrar la referencia al objeto asociado
        // romper link bi-direccional
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // borrar el instructor
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        // crear query
        TypedQuery<Course> query = entityManager.createQuery(
                                "from Course where instructor.id = :data ", Course.class);

        query.setParameter("data", theId);

        // ejecutar query
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {

        // crear query
        TypedQuery<Instructor> query = entityManager.createQuery(
                                  "select i from Instructor i "
                                    + "JOIN FETCH i.courses "
                                          + "JOIN FETCH i.instructorDetail "
                                    + "where  i.id = :data ", Instructor.class);

        query.setParameter("data", theId);

        // Ejecutar query
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor theInstructor) {

        // actualizar una entidad existente
        entityManager.merge(theInstructor);

    }

    @Override
    @Transactional
    public void update(Course theCourse) {
        entityManager.merge(theCourse);

    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        // recuperar el curso
        Course tempCourse = entityManager.find(Course.class, theId);

        // borrar el curso
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {

        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {

        // crear query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                + "JOIN FETCH c.reviews "
                + " where c.id = :data", Course.class);

        query.setParameter("data", theId);

        // ejecutar query
        Course course = query.getSingleResult();

        return course;

    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {

        // crear query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.students "
                        + " where c.id = :data", Course.class);

        query.setParameter("data", theId);

        // ejecutar query
        Course course = query.getSingleResult();

        return course;

    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {

        // crear query
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                        + "JOIN FETCH s.courses "
                        + " where s.id = :data", Student.class);

        query.setParameter("data", theId);

        // ejecutar query
        Student student = query.getSingleResult();

        return student;

    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {

        // recuperar el estudiante
        Student tempStudent = entityManager.find(Student.class, theId);
        if(tempStudent != null){
            // obtener los cursos
            List<Course>courses = tempStudent.getCourses();

            // romper la asociacion con los cursos
            for(Course tempCourse : courses){
                tempCourse.getStudents().remove(tempStudent);
            }

        // borrar el estudiante
        entityManager.remove(tempStudent);
        }
    }
}
