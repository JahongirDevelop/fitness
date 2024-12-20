package uni.project.fitness.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutritionRequestDTO {
    private String title;
    private String subTitle;
    private String image;
    private String description;
    private String pdfLink;
}

