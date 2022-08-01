package org.example.dao;

import org.example.entity.Task;

import java.util.List;

public interface TaskDao {
    void saveTask(Long id,Task task);
    void updateTask(Long id,Task task);
    List<Task> getAllTasksByLessonId(Long id);
    void deleteTaskById(Long id);
}
