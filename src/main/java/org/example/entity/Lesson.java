package org.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "lessons")
@NoArgsConstructor
@Getter
@Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lesson_name")
    private String lessonName;
    @Column(name = "video_link")
    private String videoLink;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id",nullable = false)
    private Course course;
    @OneToMany(mappedBy = "lesson",cascade ={
            CascadeType.DETACH,
            CascadeType.REMOVE,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private List<Task> tasks;

    public Lesson(String lessonName, String videoLink) {
        this.lessonName = lessonName;
        this.videoLink = videoLink;
    }
    public void  addTask(Task task){
        this.tasks.add(task);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", lessonName='" + lessonName + '\'' +
                ", videoLink='" + videoLink + '\'' +
                '}';
    }
}
