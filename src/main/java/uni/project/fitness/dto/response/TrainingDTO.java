package uni.project.fitness.dto.response;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDTO {
    private UUID id;
    private String title;
}
