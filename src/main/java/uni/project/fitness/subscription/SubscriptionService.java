package uni.project.fitness.subscription;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.project.fitness.entity.Course;
import uni.project.fitness.entity.UserEntity;
import uni.project.fitness.repository.CourseRepository;
import uni.project.fitness.repository.UserRepository;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    private final UserRepository userRepository;

    private final CourseRepository courseRepository;

    public SubscriptionDTO createSubscription(UUID userId, UUID courseId, SubscriptionPeriod period) {
        // Fetch user and course
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Get start and end dates based on subscription period
        LocalDate startDate = LocalDate.now();
        // Use period.getDuration().toDays() to ensure only days are added to LocalDate
        LocalDate endDate = startDate.plusDays(period.getDuration().toDays());

        // Create new subscription
        Subscription subscription = Subscription.builder()
                .user(user)
                .course(course)
                .period(period)
                .startDate(startDate)
                .endDate(endDate)
                .status(SubscriptionStatus.ACTIVE)
                .build();

        // Save subscription
        subscription = subscriptionRepository.save(subscription);

        // Return the DTO
        return SubscriptionDTO.builder()
                .id(subscription.getId())
                .userId(user.getId())
                .courseId(course.getId())
                .period(subscription.getPeriod())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .status(subscription.getStatus())
                .build();
    }


    public void extendSubscription(UUID userId, UUID courseId, SubscriptionPeriod additionalPeriod) {
        Subscription subscription = subscriptionRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        subscription.extendSubscription(additionalPeriod);
        subscriptionRepository.save(subscription);
    }
}

