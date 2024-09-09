package uni.project.fitness.dto.response;

import lombok.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompletedCourseDTO {
    private CourseResponseDTO courseResponseDTO;
}

