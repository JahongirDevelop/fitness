package uni.project.fitness.dto.request;

import lombok.*;

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
//    private List<UUID> teacherIds; // List of Teacher IDs
    private String trailerVideo;
    private Double price;
//    private List<UUID> trainingIds; // List of Training IDs
    private String whatToExpects;
    private String purpose;
    private List<String> icons;
    private List<String> results;
    private UUID categoryId; // Reference to Category
}
