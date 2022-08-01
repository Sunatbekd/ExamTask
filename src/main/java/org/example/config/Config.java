package org.example.config;

import org.example.entity.Course;
import org.example.entity.Instructor;
import org.example.entity.Lesson;
import org.example.entity.Task;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;
import java.util.Properties;

public class Config {
    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        properties.setProperty(Environment.URL, "jdbc:postgresql://localhost:5432/examtask");
        properties.setProperty(Environment.DRIVER, "org.postgresql.Driver");
        properties.setProperty(Environment.USER, "postgres");
        properties.setProperty(Environment.PASS, "123");
        properties.setProperty(Environment.SHOW_SQL, "true");
        properties.setProperty(Environment.HBM2DDL_AUTO, "update");
        properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Instructor.class);
        configuration.addAnnotatedClass(Lesson.class);
        configuration.addAnnotatedClass(Task.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration
                        .getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
    public static EntityManager entityManager(){
        return getSessionFactory().createEntityManager();
    }
}
