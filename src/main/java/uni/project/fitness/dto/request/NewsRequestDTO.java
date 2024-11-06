package uni.project.fitness.dto.request;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequestDTO {
    private String title;
    private String subTitle;
    private String description;
    private String media;
}
