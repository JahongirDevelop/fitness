package uni.project.fitness.dto.request;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingForCourseRequestDTO {
    private String title;
    private UUID courseId;
    private String description;
    private String shortDescription;
    private String image;
    private String video;
    private String importantInfo;
    private List<String> equipments;
    private List<String> musclesInvolved;
    private List<String> results;
}
