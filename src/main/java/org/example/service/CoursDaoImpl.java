package org.example.service;

import org.example.config.Config;
import org.example.dao.CourseDao;
import org.example.entity.Course;
import org.example.entity.Instructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class CoursDaoImpl implements CourseDao {
    @Override
    public void saveCourse(Course course) {
        try (Session session = Config.getSessionFactory().openSession();){
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
            System.out.println("save Course successful!!!");
        }catch (HibernateException i){
            System.out.println(i.getMessage());
        }
    }

    @Override
    public Course getCourseById(Long id) {
        try ( Session session = Config.getSessionFactory().openSession();){
            session.beginTransaction();
            Course course = session.get(Course.class,id);
            System.out.println("Getted Course By Id successful");
            session.getTransaction().commit();
            return course;
        }catch (HibernateException h){
            System.out.println(h.getMessage());
            return null;
        }
    }

    @Override
    public List<Course> getAllCourse() {
        try (Session session = Config.getSessionFactory().openSession()){
            session.beginTransaction();
            List<Course>courses = session.createQuery("from Course c order by c.createAt ").getResultList();
            session.getTransaction().commit();
            return courses;
        }catch (HibernateException h ){
            System.out.println(h.getMessage());
            return null;
        }
    }

    @Override
    public void updateCourse(Long id, Course course) {
        try(Session session = Config.getSessionFactory().openSession()){
            session.beginTransaction();
          Course course1 = session.find(Course.class,id);
          course1.setCourseName(course.getCourseName());
          course.setDuration(course.getDuration());
          course1.setCreateAt(course.getCreateAt());
          course1.setDescription(course.getDescription());
          course1.setImageLink(course.getImageLink());
            session.getTransaction().commit();
        }catch (HibernateException h ){
            System.out.println(h.getMessage());
        }
    }

    @Override
    public void deleteCourseById(Long id) {
        try (Session session = Config.getSessionFactory().openSession()){
            session.beginTransaction();
            Course course = session.find(Course.class,id);
            for (Instructor a : course.getInstructors()){
                a.setCourses(null);
            }
            session.delete(course);
            session.getTransaction().commit();
        }catch (HibernateException h){
            System.out.println(h.getMessage());
        }
    }

    @Override
    public Course getCourseByName(String courseName) {
        try(Session session = Config.getSessionFactory().openSession()){
            session.beginTransaction();
            Course course = session.createQuery("select c from Course c where c.courseName =: coursName",Course.class)
                    .setParameter("coursName",courseName).getSingleResult();
            session.getTransaction().commit();
            return course;
        }catch (HibernateException h){
            System.out.println(h.getMessage());
            return null;
        }
    }
}
