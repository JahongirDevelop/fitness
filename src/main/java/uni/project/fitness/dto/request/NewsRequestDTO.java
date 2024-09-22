package uni.project.fitness.dto.request;

import lombok.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequestDTO {
    private String title;
    private String subTitle;
    private String description;
    private String media;
}
