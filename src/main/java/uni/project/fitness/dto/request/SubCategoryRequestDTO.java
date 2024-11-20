package uni.project.fitness.dto.request;

import lombok.Data;
import java.util.UUID;

@Data
public class SubCategoryRequestDTO {
    private String name;
    private UUID parentCategoryId;
    private String image;
}
