package uni.project.fitness.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
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

    @Column(columnDefinition = "TEXT")
    private String description;
    private String pdfLink;

}

