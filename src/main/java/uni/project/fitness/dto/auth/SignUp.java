package uni.project.fitness.dto.auth;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SignUp {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;
}
