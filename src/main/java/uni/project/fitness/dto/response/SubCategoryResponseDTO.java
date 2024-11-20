package uni.project.fitness.dto.response;

import lombok.Data;
import java.util.List;
import java.util.UUID;
@Data
public class SubCategoryResponseDTO {
    private UUID id;
    private String name;
    private String image;
    private UUID parentCategoryId;
    private Boolean isAvailable;
    private List<CourseResponseDTO> courses;
}

