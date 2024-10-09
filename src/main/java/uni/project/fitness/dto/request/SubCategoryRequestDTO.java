package uni.project.fitness.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class SubCategoryRequestDTO {
    private String name;
    private UUID parentCategoryId; // References the top-level category it belongs to.
    private String image; // Subcategories have images.
}
