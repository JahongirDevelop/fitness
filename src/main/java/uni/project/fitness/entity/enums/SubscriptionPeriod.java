package uni.project.fitness.entity.enums;

import lombok.Getter;

import java.time.Duration;

@Getter
public enum SubscriptionPeriod {
    ONE_MONTH_BASIC(Duration.ofDays(30)),
    ONE_MONTHS_FULL(Duration.ofDays(30)),
    ONE_YEAR_FULL(Duration.ofDays(365));

    private final Duration duration;

    SubscriptionPeriod(Duration duration) {
        this.duration = duration;
    }

}


