package uni.project.fitness.dto.response;

import lombok.*;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponseDTO {
    private UUID id;
    private String title;
    private String subTitle;
    private String description;
    private String media;
//    private CategoryDTO category;  // Assuming you need a DTO for Category as well
}
