package uni.project.fitness.entity;

import jakarta.persistence.*;
import lombok.*;
import uni.project.fitness.entity.enums.SubscriptionPeriod;
import uni.project.fitness.entity.enums.SubscriptionStatus;
import java.time.LocalDate;
@Entity(name = "subscription")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Subscription extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private SubscriptionPeriod period;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    public boolean isActive() {
        return status == SubscriptionStatus.ACTIVE && LocalDate.now().isBefore(endDate);
    }

    // Extend subscription by the given period duration
    public void extendSubscription(SubscriptionPeriod additionalPeriod) {
        this.endDate = this.endDate.plus(additionalPeriod.getDuration());
    }

}

