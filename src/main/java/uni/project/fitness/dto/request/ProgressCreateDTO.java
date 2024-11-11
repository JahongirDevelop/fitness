package uni.project.fitness.dto.request;

import lombok.*;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressCreateDTO {
    private List<String> pictures;
    private Integer chest;
    private Integer hips;
    private Integer waist;
    private Integer weight;
    private UUID userId;
}

