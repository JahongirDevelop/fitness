package uni.project.fitness.dto.response;

import lombok.*;
import uni.project.fitness.entity.enums.UserRole;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileResponse {
    private UUID id;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
}
