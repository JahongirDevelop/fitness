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
public class CategoryResponseDTO {
    private UUID id;
    private String name;
    private UUID parentCategoryId; // ID of the parent category (if any)
    private List<CategoryResponseDTO> subcategories; // For nested subcategories
    private List<CourseResponseDTO> courses; // List of courses in the category
}


