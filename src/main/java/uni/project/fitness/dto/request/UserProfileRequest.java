package uni.project.fitness.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileRequest {
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
