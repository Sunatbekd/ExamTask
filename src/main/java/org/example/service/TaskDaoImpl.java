package org.example.service;

import org.example.config.Config;
import org.example.dao.TaskDao;
import org.example.entity.Lesson;
import org.example.entity.Task;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;

public class TaskDaoImpl implements TaskDao {
    @Override
    public void saveTask(Long id, Task task) {
        try(Session session = Config.getSessionFactory().openSession()) {
            session.beginTransaction();
            Lesson lesson = session.find(Lesson.class,id);
            lesson.addTask(task);
            task.setLesson(lesson);
            session.save(task);
            session.getTransaction().commit();
        }catch (HibernateException h){
            System.out.println(h.getMessage());
        }
    }

    @Override
    public void updateTask(Long id, Task task) {
        try(Session session = Config.getSessionFactory().openSession()){
            session.beginTransaction();
            Task task1 = session.find(Task.class,id);
            task1.setTaskName(task.getTaskName());
            task1.setDeadline(task.getDeadline());
            task1.setTask(task.getTask());
            session.getTransaction().commit();

        }catch (HibernateException h){
            System.out.println(h.getMessage());
        }
    }

    @Override
    public List<Task> getAllTasksByLessonId(Long id) {
        EntityManager entityManager = Config.entityManager();
        entityManager.getTransaction().begin();
        Lesson lesson = entityManager.find(Lesson.class,id);
        List<Task>tasks = lesson.getTasks();
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println("it's work");
        return tasks;
    }

    @Override
    public void deleteTaskById(Long id) {
        try (Session session = Config.getSessionFactory().openSession()){
            session.beginTransaction();
            Task task = session.find(Task.class,id);
            session.delete(task);
            session.getTransaction().commit();
        }catch (HibernateException h){
            System.out.println(h.getMessage());
        }
    }
}
