package org.example;

import org.example.config.Config;
import org.example.dao.CourseDao;
import org.example.dao.InstructorDao;
import org.example.dao.LessonDao;
import org.example.dao.TaskDao;
import org.example.entity.Course;
import org.example.entity.Instructor;
import org.example.entity.Lesson;
import org.example.entity.Task;
import org.example.service.CoursDaoImpl;
import org.example.service.InstructorDaoImpl;
import org.example.service.LessonDaoImpl;
import org.example.service.TaskDaoImpl;

import java.time.LocalDate;

public class App {
    public static void main( String[] args ) {
//        Config.getSessionFactory();

        CourseDao courseDao = new CoursDaoImpl();
//        courseDao.saveCourse(
//               new Course("Java-6",10,(LocalDate.of(2022,9,30)),"https://images.app.goo.gl/CaVHHLrAJ5kqNVMK9","Java-6"));
//        System.out.println(courseDao.getCourseById(2L));
//        System.out.println(courseDao.getAllCourse());
//        courseDao.deleteCourseById(2L);
   //    courseDao.updateCourse(1L,new Course("java-10",8,(LocalDate.of(2002,8,23)),"java.com","java"));
//        System.out.println(courseDao.getCourseByName("java-10"));

        InstructorDao instructorDao = new InstructorDaoImpl();
//        instructorDao.saveInstructor(new Instructor("Nurisa","Nurva","za@gmail.com","077777777777"));
//        instructorDao.updateInstructor(3L,new Instructor("Muhammed","Allanov","nurisa@gmail.com","0777777777778"));
//        System.out.println(instructorDao.getInstructorById(2L));
//        System.out.println(instructorDao.getInstructorsByCourseId(1L));
//     instructorDao.assignInstructorToCourse(2L,1L);
//       instructorDao.deleteInstructorById(3L);

        LessonDao lessonDao = new LessonDaoImpl();
//        lessonDao.saveLesson(3L,new Lesson("For loop","www.fdghhkhjfhg.ru"));
//        System.out.println(lessonDao.getLessonById(1L));
//        System.out.println(lessonDao.getLessonsByCourseId(3L));
//        lessonDao.updateLesson(3L,new Lesson("Wile loopss","www.fdghhkdthf.ru"));

        TaskDao taskDao = new TaskDaoImpl();
        taskDao.saveTask(1L,new Task("Task1", (LocalDate.of(2023,8,30)),"Task very goods"));
     //   taskDao.updateTask(1L,new Task("Task3", (LocalDate.of(2023,7,30)),"Task very very good"));
//        taskDao.deleteTaskById(1L);
//        System.out.println(taskDao.getAllTasksByLessonId(2L));

    }
}
