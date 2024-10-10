package uni.project.fitness.dto.response;

import lombok.*;
import uni.project.fitness.entity.enums.SubscriptionPeriod;
import uni.project.fitness.entity.enums.SubscriptionStatus;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionDTO {
    private UUID id;
    private UUID userId;
    private SubscriptionPeriod period;
    private LocalDate startDate;
    private LocalDate endDate;
    private SubscriptionStatus status;
}

