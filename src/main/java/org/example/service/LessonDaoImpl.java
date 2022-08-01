package org.example.service;

import org.example.config.Config;
import org.example.dao.LessonDao;
import org.example.entity.Course;
import org.example.entity.Lesson;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class LessonDaoImpl implements LessonDao {


    @Override
    public void saveLesson(Long id, Lesson lesson) {
        try (Session session = Config.getSessionFactory().openSession()) {
            session.beginTransaction();
            Course course = session.find(Course.class, id);
            lesson.setCourse(course);
            session.save(lesson);
            session.getTransaction().commit();
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
        }
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        try(Session session = Config.getSessionFactory().openSession()){
            session.beginTransaction();
            Lesson lesson1 = session.find(Lesson.class,id);
            lesson1.setLessonName(lesson.getLessonName());
            lesson1.setVideoLink(lesson.getVideoLink());
            session.getTransaction().commit();

        }catch (HibernateException h){
            System.out.println(h.getMessage());
        }
    }

    @Override
    public Lesson getLessonById(Long id) {
        try (Session session = Config.getSessionFactory().openSession()) {
            session.beginTransaction();
            Lesson lesson = session.get(Lesson.class, id);
            session.getTransaction().commit();
            return lesson;
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
            return null;
        }
    }

    @Override
    public List<Lesson> getLessonsByCourseId(Long id) {

        EntityManager entityManager = Config.entityManager();

        entityManager.getTransaction().begin();
        Course course = entityManager.find(Course.class, id);
        List<Lesson> lessons = course.getLessons();
        entityManager.getTransaction().commit();
        entityManager.close();
        return lessons;
    }
}
