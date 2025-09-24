package com.lara.cruddemo;

import com.lara.cruddemo.dao.StudentDAO;
import com.lara.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return  runner -> {
               // createStudent(studentDAO);

               createMultipleStudents(studentDAO);

              // readStudent(studentDAO);

             // queryForStudents(studentDAO);
            
             // queryForStudentsByLastName(studentDAO);

             // updateStudent(studentDAO);
            
             // deleteStudent(studentDAO);

            // deleteAllStudents(studentDAO);

        };
    }

    private void deleteAllStudents(StudentDAO studentDAO) {

        System.out.println("Deleting all students");
        int numRowsDeleted = studentDAO.deleteAll();
        System.out.println("Deleted row count: " + numRowsDeleted);
    }

    private void deleteStudent(StudentDAO studentDAO) {

        int studentId = 3;
        System.out.println("Deleting student id: " + studentId);
        studentDAO.delete(studentId);
    }

    private void updateStudent(StudentDAO studentDAO) {

        // obtener estudiante segun el id (PK)
        int studentId = 1;
        System.out.println("Getting sudent with id: " + studentId);
        Student myStudent = studentDAO.findById(studentId);

        // cambiar first name a "John"
        System.out.println("Updating student...");
        myStudent.setFirstName("John");

        // actualizar el estudiante
        studentDAO.update(myStudent);

        // mostrar el estudiante actualizado
        System.out.println("Updated student" + myStudent);
    }

    private void queryForStudentsByLastName(StudentDAO studentDAO) {

        // obtener lista estudiantes
        List<Student> theStudents = studentDAO.findByLastName("Doe");

        //mostrar lista estudiantes
        for(Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }

    private void queryForStudents(StudentDAO studentDAO) {

        // obtener lista de estudiantes
        List<Student> theStudents = studentDAO.findAll();

        //mostrar lista de estudiantes
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }

    private void readStudent(StudentDAO studentDAO) {

        // crear un objeto estudiante
        System.out.println("Creating new student object...");
        Student tempStudent = new Student("Daffy", "Duck", "daffy@lara.com");

        // guardar el estudiante
        System.out.println("Saving the student...");
        studentDAO.save(tempStudent);

        // mostrar e id del estudiante guardado
        int theId = tempStudent.getId();
        System.out.println("Saved student. Generated id: " + theId);

        //recuperar estudiante mediante el id (PK)
        System.out.println("Retrieving student with id: " + theId);
        Student myStudent = studentDAO.findById(theId);

        //mostrar el estudiante
        System.out.println("Found the student: " + myStudent);
    }

    private void createMultipleStudents(StudentDAO studentDAO) {

        // crear varios estudiantes
        System.out.println("Creating 3 student objects...");
        Student tempStudent1 = new Student("John", "Doe", "john@lara.com");
        Student tempStudent2 = new Student("Mary", "Public", "mary@lara.com");
        Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@lara.com");

        // guardar los objetos estudiantes
        System.out.println("Saving the students...");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);

    }

    private void createStudent(StudentDAO studentDAO) {

        // crear el objeto student
        System.out.println("Creating new student");
        Student tempStudent = new Student("Paul", "Doe", "paul@lara.com");

        // guardar el objeto student
        System.out.println("Saving the student...");
        studentDAO.save(tempStudent);

        //mostrar el id del student guardado
        System.out.println("Saved student. Generated id: "  + tempStudent.getId());
    }
}
