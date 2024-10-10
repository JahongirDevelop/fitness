package uni.project.fitness.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.*;
import uni.project.fitness.entity.icons.IconDescription;

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

    // Store the description as a single text field in the database
    @Column(columnDefinition = "TEXT")
    private String description;
    private String pdfLink;

}

