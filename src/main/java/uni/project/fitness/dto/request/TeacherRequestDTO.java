package uni.project.fitness.dto.request;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherRequestDTO {
    private String name;
    private String image;
    private String workType;
    private String about;
}
