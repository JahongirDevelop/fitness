package uni.project.fitness.dto.response;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherResponseDTO {
    private UUID id;
    private String name;
    private String image;
    private String workType;
    private String about;
}
