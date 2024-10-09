package uni.project.fitness.dto.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class TopCategoryResponseDTO {
    private UUID id;
    private String name;
    private List<SubCategoryResponseDTO> subcategories;
}

