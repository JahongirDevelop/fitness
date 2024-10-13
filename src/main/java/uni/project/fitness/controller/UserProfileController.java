package uni.project.fitness.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.fitness.dto.request.UserProfileRequest;
import uni.project.fitness.dto.response.CompletedCourseDTO;
import uni.project.fitness.servise.interfaces.UserProfileService;
import uni.project.fitness.servise.interfaces.CompletedCourseService;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;
    private final CompletedCourseService completedCourseService;
    @GetMapping("/me")
    public ResponseEntity<?> getUserProfile(Principal principal) {
        return ResponseEntity.ok(userProfileService.getUserProfile(UUID.fromString(principal.getName())));
    }
    @PatchMapping("/update-profile")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserProfileRequest updatedUser, Principal principal) {
        return ResponseEntity.ok(userProfileService.updateUserProfile(UUID.fromString(principal.getName()), updatedUser));
    }
    @GetMapping("/my-completed-courses")
    public ResponseEntity<List<CompletedCourseDTO>> getCompletedCourses(Principal principal) {
        List<CompletedCourseDTO> completedCourses = completedCourseService.getCompletedCoursesByUserId(UUID.fromString(principal.getName()));
        return ResponseEntity.ok(completedCourses);
    }
}
