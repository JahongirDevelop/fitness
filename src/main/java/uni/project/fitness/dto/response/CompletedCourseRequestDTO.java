package uni.project.fitness.dto.response;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;
@Data
@Builder
public class CompletedCourseRequestDTO {
    private UUID courseId;
    private UUID userId;
}

