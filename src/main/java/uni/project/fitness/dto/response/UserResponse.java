package uni.project.fitness.dto.response;

import lombok.*;
import uni.project.fitness.entity.enums.UserRole;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private UUID id;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private UserRole role;
}
