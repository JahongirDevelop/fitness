package uni.project.fitness.dto.request;

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
public class CategoryRequestDTO {
    private String name;
    private UUID parentCategoryId; // Set this to null for top-level categories
}


