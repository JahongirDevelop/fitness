package uni.project.fitness.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.response.SubscriptionDTO;
import uni.project.fitness.entity.Course;
import uni.project.fitness.entity.Subscription;
import uni.project.fitness.entity.UserEntity;
import uni.project.fitness.entity.enums.SubscriptionPeriod;
import uni.project.fitness.entity.enums.SubscriptionStatus;
import uni.project.fitness.repository.CourseRepository;
import uni.project.fitness.repository.SubscriptionRepository;
import uni.project.fitness.repository.UserRepository;
import uni.project.fitness.servise.interfaces.SubscriptionService;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    @Override
    public SubscriptionDTO createSubscription(UUID userId, UUID courseId, SubscriptionPeriod period) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(period.getDuration().toDays());

        Subscription subscription = Subscription.builder()
                .user(user)
                .course(course)
                .period(period)
                .startDate(startDate)
                .endDate(endDate)
                .status(SubscriptionStatus.ACTIVE)
                .build();

        subscription = subscriptionRepository.save(subscription);

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

    @Override
    public void extendSubscription(UUID userId, UUID courseId, SubscriptionPeriod additionalPeriod) {
        Subscription subscription = subscriptionRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        subscription.extendSubscription(additionalPeriod);
        subscriptionRepository.save(subscription);
    }
}
