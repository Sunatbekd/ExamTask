package org.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "corse_name")
    private String courseName;
    private int duration;
    @Column(name = "create_at")
    private LocalDate createAt;
    @Column(name = "image_link")
    private String imageLink;
    private String description;
    @ManyToMany(mappedBy = "courses", cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private List<Instructor>instructors;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Lesson>lessons;

    public Course(String courseName, int duration, LocalDate createAt, String imageLink, String description) {
        this.courseName = courseName;
        this.duration = duration;
        this.createAt = createAt;
        this.imageLink = imageLink;
        this.description = description;
    }
    public void  addInstructor(Instructor instructor){
        if (instructors==null){
             new ArrayList<>();
        }else {
            instructor.getCourses().add(this);
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", duration=" + duration +
                ", createAt=" + createAt +
                ", imageLink='" + imageLink + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
