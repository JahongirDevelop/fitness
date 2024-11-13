package uni.project.fitness.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity(name = "teacher")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Teacher extends BaseEntity {
    private String name;
    private String image;
    private String workType;
    private String about;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Training> trainingList;
}



