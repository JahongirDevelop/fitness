package uni.project.fitness.servise;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.request.UserProfileRequest;
import uni.project.fitness.dto.response.UserProfileResponse;
import uni.project.fitness.entity.UserEntity;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.repository.UserRepository;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class UserProfileService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserProfileResponse getUserProfile(UUID userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        return UserProfileResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public UserProfileResponse updateUserProfile(UUID userId, UserProfileRequest profileRequest) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

//        // Check if the current password matches the user's password
//        if (!passwordEncoder.matches(profileRequest.getCurrentPassword(), user.getPassword())) {
//            throw new IllegalArgumentException("Invalid current password");
//        }

        if (profileRequest.getName() != null) {
            user.setName(profileRequest.getName());
        }

        if (profileRequest.getEmail() != null) {
            user.setEmail(profileRequest.getEmail());
        }
        if (profileRequest.getPhoneNumber() != null) {
            user.setPhoneNumber(profileRequest.getPhoneNumber());
        }
        // Save the updated user entity
        UserEntity updatedUser = userRepository.save(user);

        // Return the updated user profile response
        return UserProfileResponse.builder()
                .id(updatedUser.getId())
                .name(updatedUser.getName())
                .email(updatedUser.getEmail())
                .phoneNumber(updatedUser.getPhoneNumber())
                .build();
    }

}
