package uni.project.fitness.dto.response;

import lombok.*;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingForTeacherResponseDTO {
    private UUID id;
    private String title;
    private TeacherResponseDTO teacher;
    private String description;
    private String shortDescription;
    private String image;
    private String video;
    private String importantInfo;
    private List<String> equipments;
    private List<String> musclesInvolved;
    private List<String> results;
    private Boolean isAvailable;
}
