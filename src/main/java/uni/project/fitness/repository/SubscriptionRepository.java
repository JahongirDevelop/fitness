package uni.project.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.project.fitness.entity.Subscription;

import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    Optional<Subscription> findByUserId(UUID userId);
}

