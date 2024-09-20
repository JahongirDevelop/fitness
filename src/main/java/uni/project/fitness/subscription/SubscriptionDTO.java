package uni.project.fitness.subscription;

import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionDTO {
    private UUID id;
    private UUID userId;
    private UUID courseId;
    private SubscriptionPeriod period;
    private LocalDate startDate;
    private LocalDate endDate;
    private SubscriptionStatus status;
}

