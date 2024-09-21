package uni.project.fitness.dto.response;

import lombok.*;
import uni.project.fitness.entity.enums.UserRole;

import java.time.LocalDate;
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
    private Double height;
    private Double currentWeight;
    private Double targetWeight;
    private LocalDate birthDay;
    private String profilePicture;
}
