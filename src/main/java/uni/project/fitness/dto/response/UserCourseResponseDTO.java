package uni.project.fitness.dto.response;

import lombok.*;
import uni.project.fitness.entity.enums.IconDescription;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCourseResponseDTO  {
    private UUID id;
    private String title;
    private String subTitle;
    private String description;
    private String image;
    private String trailerVideo;
    private List<TrainingDTO> trainings;
    private List<IconDescription> whatYouWillGet; // Use IconDescription enum
    private List<String> whatToExpects;
    private String purpose;
    private List<String> results;
    private CategoryDTO category;
    private Boolean isAccessible; // True if the user has an active subscription

}
