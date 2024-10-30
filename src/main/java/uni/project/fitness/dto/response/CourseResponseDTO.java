package uni.project.fitness.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uni.project.fitness.entity.enums.IconDescription;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponseDTO {
    private UUID id;
    private String title;
    private String subTitle;
    private String description;
    private String image;
    private String trailerVideo;
    private List<TrainingDTO> trainings;
    private List<IconDescription.IconObject> whatYouWillGet;
    private List<String> whatToExpects;
    private String purpose;
    private List<String> results;
    private CategoryDTO category;

}
