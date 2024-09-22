package uni.project.fitness.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "news")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class News extends BaseEntity {
    private String title;
    private String subTitle;
    private String description;
    private String media;
}


