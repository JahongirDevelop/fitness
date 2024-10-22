package uni.project.fitness.dto.request;

import lombok.*;
import uni.project.fitness.entity.enums.IconDescription;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequestDTO {
    private String title;
    private String subTitle;
    private String description;
    private String image;
    private String trailerVideo;
    private List<IconDescription> whatYouWillGet; // Use IconDescription enum
    private List<String> whatToExpects;
    private String purpose;
    private List<String> results;
    private UUID categoryId;
}