package uni.project.fitness.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetDto {
    private String token;
    private String newPassword;
}