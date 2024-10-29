package uni.project.fitness.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTOForUser {
    private UUID id;
    private String name;
    private List<UserCourseResponseDTO> courses;
}
