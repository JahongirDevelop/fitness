package uni.project.fitness.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressResponseDTO {
    private UUID id;
    private List<String> pictures;
    private Integer chest;
    private Integer hips;
    private Integer waist;
    private Integer weight;
    private UUID userId;
    private LocalDate date;
}

