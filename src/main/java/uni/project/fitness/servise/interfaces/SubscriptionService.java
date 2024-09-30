package uni.project.fitness.servise.interfaces;

import uni.project.fitness.dto.response.SubscriptionDTO;
import uni.project.fitness.entity.enums.SubscriptionPeriod;

import java.util.UUID;

public interface SubscriptionService {
    SubscriptionDTO createSubscription(UUID userId, UUID courseId, SubscriptionPeriod period);
    void extendSubscription(UUID userId, UUID courseId, SubscriptionPeriod additionalPeriod);
}
