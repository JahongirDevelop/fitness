package uni.project.fitness.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "completed_course")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CompletedCourse extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}

