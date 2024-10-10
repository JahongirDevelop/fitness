package uni.project.fitness.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.fitness.servise.interfaces.CourseService;
import uni.project.fitness.dto.response.SubscriptionDTO;
import uni.project.fitness.entity.enums.SubscriptionPeriod;
import uni.project.fitness.servise.interfaces.SubscriptionService;
import java.util.UUID;
@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    @PostMapping("/subscribe")
    public ResponseEntity<SubscriptionDTO> subscribe(@RequestParam UUID userId, @RequestParam SubscriptionPeriod period) {
        SubscriptionDTO subscriptionDTO = subscriptionService.createSubscription(userId, period);
        return ResponseEntity.ok(subscriptionDTO);
    }

    @PostMapping("/extend")
    public ResponseEntity<String> extendSubscription(@RequestParam UUID userId, @RequestParam UUID courseId, @RequestParam SubscriptionPeriod period) {
        subscriptionService.extendSubscription(userId, courseId, period);
        return ResponseEntity.ok("Subscription extended successfully for the course");
    }
//    @GetMapping("/get-course-for-user/{courseId}/{userId}")
//    public ResponseEntity<UserCourseResponseDTO> getCourseForUser(@PathVariable UUID courseId, @PathVariable UUID userId) {
//        return ResponseEntity.ok(courseService.getCourseForUser(courseId, userId));
//    }
}

