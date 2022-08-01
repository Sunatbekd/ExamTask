package org.example.service;

import org.example.config.Config;
import org.example.dao.InstructorDao;
import org.example.entity.Course;
import org.example.entity.Instructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;

public class InstructorDaoImpl implements InstructorDao {
    @Override
    public void saveInstructor(Instructor instructor) {
        try (Session session = Config.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(instructor);
            session.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
        }
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        try (Session session = Config.getSessionFactory().openSession()) {
            session.beginTransaction();
            Instructor instructor1 = session.find(Instructor.class, id);
            instructor1.setFirstName(instructor.getFirstName());
            instructor1.setLastName(instructor.getLastName());
            instructor1.setEmail(instructor.getEmail());
            instructor1.setPhoneNumber(instructor.getPhoneNumber());
            session.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
        }
    }

    @Override
    public Instructor getInstructorById(Long id) {
        try (Session session = Config.getSessionFactory().openSession()) {
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, id);
            session.getTransaction().commit();
            return instructor;
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
            return null;
        }
    }

    @Override
    public List<Instructor> getInstructorsByCourseId(Long id) {
        try (Session session = Config.getSessionFactory().openSession()) {
            session.beginTransaction();
            Course course1 = session.find(Course.class, id);
            List<Instructor> instructors = course1.getInstructors();
            session.getTransaction().commit();
            return instructors;
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
            return null;
        }

    }

    @Override
    public void deleteInstructorById(Long id) {
        try (Session session = Config.getSessionFactory().openSession()) {
            session.beginTransaction();
            Instructor instructor = session.find(Instructor.class, id);
            session.delete(instructor);
            session.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
        }
    }

    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseId) {

        EntityManager entityManager = Config.entityManager();
        entityManager.getTransaction().begin();
        Instructor instructor = entityManager.find(Instructor.class, instructorId);
        Course course = entityManager.find(Course.class, courseId);
        instructor.addCourse(course);
        course.addInstructor(instructor);
        entityManager.persist(course);
        entityManager.persist(instructor);
        entityManager.getTransaction().commit();
        System.out.println("commit");
        entityManager.close();
        }
}
