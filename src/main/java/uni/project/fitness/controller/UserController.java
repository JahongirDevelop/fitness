package uni.project.fitness.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uni.project.fitness.dto.response.UserResponse;
import uni.project.fitness.entity.enums.UserRole;
import uni.project.fitness.servise.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Optional<UserResponse>> getUserById(@PathVariable UUID id) {
         return ResponseEntity.ok(userService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(userService.deleteUser(id));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PutMapping("/update-user-role/{userId}/role")
    public ResponseEntity<UserResponse> updateUserRole(
            @PathVariable UUID userId,
            @RequestParam UserRole role) {
        UserResponse userResponse = userService.updateUserRole(userId, role);
        return ResponseEntity.ok(userResponse);
    }


    @GetMapping("/get-role")
    public Object getCurrentUser2(Authentication authentication) {
        return authentication.getAuthorities();
    }


    @GetMapping("/get-id")
    public Object getCurrentUser4(Authentication authentication) {
        return authentication.getName();
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

}
