package uni.project.fitness.entity.enums;

import java.time.Duration;

public enum SubscriptionPeriod {
    ONE_DAY(Duration.ofDays(1)),
    ONE_MONTH(Duration.ofDays(30)),
    THREE_MONTHS(Duration.ofDays(90)),
    SIX_MONTHS(Duration.ofDays(180)),
    ONE_YEAR(Duration.ofDays(365));

    private final Duration duration;

    SubscriptionPeriod(Duration duration) {
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }
}

