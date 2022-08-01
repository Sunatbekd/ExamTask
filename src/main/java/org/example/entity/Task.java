package org.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "task_name")
    private String taskName;
    private LocalDate deadline;
    private String task;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    public Task(String taskName, LocalDate deadline, String task ) {
        this.taskName = taskName;
        this.deadline = deadline;
        this.task = task;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", deadline=" + deadline +
                ", task='" + task + '\'' +
                '}';
    }
}
