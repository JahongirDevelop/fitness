package uni.project.fitness.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutritionResponseDTO {
    private UUID id;
    private String title;
    private String subTitle;
    private String image;
    private String description;
    private String pdfLink;
}

