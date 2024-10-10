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

    // Determine which content is unlocked based on subscription period
    public boolean isBasicAccess() {
        return period == SubscriptionPeriod.ONE_MONTH_BASIC;
    }

    public boolean isFullAccess() {
        return period == SubscriptionPeriod.ONE_MONTHS_FULL || period == SubscriptionPeriod.ONE_YEAR_FULL;
    }

    public boolean isYearlyFullAccess() {
        return period == SubscriptionPeriod.ONE_YEAR_FULL;
    }

    // Method to check if a specific type of content is unlocked
    public boolean isTrainingUnlocked() {
        return isBasicAccess() || isFullAccess();
    }

    public boolean isNutritionUnlocked() {
        return isFullAccess() || isYearlyFullAccess();
    }

    public boolean isTeacherTrainingUnlocked() {
        return isFullAccess() || isYearlyFullAccess();
    }
}

