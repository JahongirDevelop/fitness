package uni.project.fitness.entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;
@Entity(name = "nutrition")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Nutrition extends BaseEntity {
    private String title;
    private String subTitle;
    private String image;
    private List<String> description;
}
