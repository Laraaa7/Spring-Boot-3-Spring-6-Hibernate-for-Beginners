package com.lara.cruddemo;

import com.lara.cruddemo.dao.AppDAO;
import com.lara.cruddemo.entity.Course;
import com.lara.cruddemo.entity.Instructor;
import com.lara.cruddemo.entity.InstructorDetail;
import com.lara.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {

        return runner -> {

            // createCourseAndReviews(appDAO);
            // retrieveCourseAndReviews(appDAO);
            deleteCourseAndReviews(appDAO);
        };
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {

        int theId = 10;
        System.out.println("Deleting course id: " + theId);
        appDAO.deleteCourseById(theId);

        System.out.println("Done!");
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {

        // obtener el curso y reviews
        int theId = 10;
        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

        // imprimir el curso
        System.out.println(tempCourse);

        // imprimir las reviews
        System.out.println(tempCourse.getReviews());

        System.out.println("Done!");
    }

    private void createCourseAndReviews(AppDAO appDAO) {

        // crear un curso
        Course tempCourse = new Course("Pacman - How To Score One Million Points");

        // añadir reviews
        tempCourse.addReview(new Review("Great course.. I loved it!"));
        tempCourse.addReview(new Review("Cool course, job well done."));
        tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

        // guardar el curso
        System.out.println("Saving the course");
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());

        appDAO.save(tempCourse);

        System.out.println("Done!!");
    }

    private void deleteCourse(AppDAO appDAO) {

        int theId = 10;
        System.out.println("Deleting course id " + theId);

        appDAO.deleteCourseById(theId);
        System.out.println("Done!");
    }

    private void updateCourse(AppDAO appDAO) {

        int theId = 10;

        // encontrar el curso
        System.out.println("Finding course: " + theId);
        Course tempCourse = appDAO.findCourseById(theId);

        // actualizar el curso
        System.out.println("Updating course id: " + theId);
        tempCourse.setTitle("Enjoy the Simple Things");

        appDAO.update(tempCourse);

        System.out.println("Done!!!");
    }

    private void updateInstructor(AppDAO appDAO) {

        int theId = 1;

        // encontrar el instructor
        System.out.println("Finding instructor: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);

        // actualizar el instructor
        System.out.println("Updating instructor id: " + theId);
        tempInstructor.setLastName("TESTER");

        appDAO.update(tempInstructor);

        System.out.println("Done!!!");

    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

        int theId = 1;

        // encontrar el instructor
        System.out.println("Finding instructor id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");

    }

    private void findCoursesForInstructor(AppDAO appDAO) {

        // buscar el instructor
        int theId = 1;
        System.out.println("Finding instructor id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);


        // buscar cursos por instructor
        System.out.println("Finding courses for instructor id: " + theId);
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);

        // asociar objetos
        tempInstructor.setCourses(courses);

        System.out.println("the associated courses: " + tempInstructor.getCourses());
        System.out.println("Done!");
    }

    private void findInstructorWithCourses(AppDAO appDAO) {

        int theId = 1;
        System.out.println("Finding instructor id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        // crear instructor
        Instructor tempInstructor =
                new Instructor("Susan", "Public", "susan.public@luv2code.com");

        // crear instructor detail
        InstructorDetail tempInstructorDetail =
                new InstructorDetail("http://www.youtube.com", "Video Games");

        // asociar los objetos
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // crear cursos
        Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
        Course tempCourse2 = new Course("The Pinball Masterclass");

        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        // guardar instructor en la bbdd
        // tambien guarda los cursos po rel CascadeType.PERSIST
        System.out.println("Saving the instructor: " +  tempInstructor);
        System.out.println("The courses: " + tempInstructor.getCourses());
        appDAO.save(tempInstructor);

        System.out.println("Done :)");
    }

    private void deleteInstructorDetail(AppDAO appDAO) {

        int theId = 3;
        System.out.println("Deleting instructor detail id: " + theId);
        appDAO.deleteInstructorDetailById(theId);

        System.out.println("Done!!");
    }

    private void findInstructorDetail(AppDAO appDAO) {

        //obtener el detail object del instrucotor
        int theId = 2;
        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

        //imprimir constructor detail
        System.out.println("tempInstructorDetail: " + tempInstructorDetail);

        //imprimir instructor asociado
        System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

        System.out.println("Done!!");
    }

    private void deleteInstructor(AppDAO appDAO) {

        int theId = 1;
        System.out.println("Deleting instructor id: " + theId);
        appDAO.deleteInstructorById(theId);

        System.out.println("Done!!");
    }

    private void findInstructor(AppDAO appDAO) {

        int theId = 2;
        System.out.println("Finding instructor id: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associate instructorDetail only: " + tempInstructor.getInstructorDetail());
    }


    private void createInstructor(AppDAO appDAO) {

        /*
        // crear instructor
        Instructor tempInstructor =
                new Instructor("Chad", "Darby", "darby@luv2code.com");

        // crear instructor detail
        InstructorDetail tempInstructorDetail =
                new InstructorDetail("http://www.luv2code.com/youtube", "Luv 2 code");
*/
        // crear instructor
        Instructor tempInstructor =
                new Instructor("Madhu", "Patel", "madhu@luv2code.com");

        // crear instructor detail
        InstructorDetail tempInstructorDetail =
                new InstructorDetail("http://www.luv2code.com/youtube", "Guitar");

        // asociar los objetos
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // guardar el instructor
        // Esto tambien guardara los detalles del objeto
        // Por el CascadeType.ALL
        System.out.println("Saving instructor: " + tempInstructor);
        appDAO.save(tempInstructor);

        System.out.println("Done!");
    }
}
