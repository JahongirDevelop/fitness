package uni.project.fitness.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity(name = "course")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Course extends BaseEntity {
    private String title;
    private String subTitle;
    private String description;
    private String image;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Training> trainings;

    private String trailerVideo;
    private Double price;

    private String whatToExpects;
    private String purpose; // maqsadi, kurs sizga nima beradi

    @ElementCollection
    private List<String> icons;

    @ElementCollection
    private List<String> results; // nima natijalar olasiz bu kursda

    @ManyToOne
    @JsonBackReference
    private Category category;
}


