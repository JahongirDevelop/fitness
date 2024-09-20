package uni.project.fitness.subscription;

import jakarta.persistence.*;
import lombok.*;
import uni.project.fitness.entity.BaseEntity;
import uni.project.fitness.entity.Course;
import uni.project.fitness.entity.UserEntity;

import java.time.LocalDate;

@Entity(name = "subscription")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Subscription extends BaseEntity {

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private Course course;  // Reference to the specific course

    @Enumerated(EnumType.STRING)
    private SubscriptionPeriod period;  // Subscription period (1 MONTH, 3 MONTHS, etc.)

    private LocalDate startDate;        // Subscription start date
    private LocalDate endDate;          // Subscription end date

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;  // ACTIVE, EXPIRED, etc.

    public boolean isActive() {
        return status == SubscriptionStatus.ACTIVE && LocalDate.now().isBefore(endDate);
    }

    public void extendSubscription(SubscriptionPeriod additionalPeriod) {
        this.endDate = this.endDate.plus(additionalPeriod.getDuration());
    }
}

