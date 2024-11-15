package uni.project.fitness.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity(name = "training")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Training extends BaseEntity {
    private String title;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonBackReference  // Prevents recursion on teacher side
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference  // Prevents recursion on course side
    private Course course;

    private String description;
    private String shortDescription;
    private String image;
    private String video;
    private String importantInfo; // muhim ma'lumotlar

    @ElementCollection
    private List<String> equipments; // kerakli jihozlar

    @ElementCollection
    private List<String> musclesInvolved; // mushaklar ishtiroki, qayerlarga ishlanadi

    @ElementCollection
    private List<String> results; // nima natijalar olasiz bu treningda


}
