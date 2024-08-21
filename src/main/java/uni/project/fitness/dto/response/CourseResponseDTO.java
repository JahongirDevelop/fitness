package uni.project.fitness.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
//    private List<TeacherResponseDTO> teachers;
    private String trailerVideo;
    private Double price;
    private List<TrainingDTO> trainings;
    private String whatToExpects;
    private String purpose;
    private List<String> icons;
    private List<String> results;
    private CategoryDTO category; // Include Category details
}
