package uni.project.fitness.dto.auth;

import jakarta.persistence.*;
import lombok.*;
import uni.project.fitness.entity.UserEntity;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity user;

    @Column(nullable = false)
    private LocalDateTime expiryDate;
}
