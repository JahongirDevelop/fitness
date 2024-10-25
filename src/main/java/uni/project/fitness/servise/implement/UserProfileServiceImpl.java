package uni.project.fitness.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.request.UserProfileRequest;
import uni.project.fitness.dto.response.UserProfileResponse;
import uni.project.fitness.entity.UserEntity;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.repository.UserRepository;
import uni.project.fitness.servise.interfaces.UserProfileService;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserProfileResponse getUserProfile(UUID userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        return getUserProfileResponse(user);
    }

    private UserProfileResponse getUserProfileResponse(UserEntity user) {
        return UserProfileResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .birthDay(user.getBirthDay())
                .currentWeight(user.getCurrentWeight())
                .targetWeight(user.getTargetWeight())
                .height(user.getHeight())
                .profilePicture(user.getProfilePicture())
                .build();
    }
    @Override
    public UserProfileResponse updateUserProfile(UUID userId, UserProfileRequest profileRequest) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        if (profileRequest.getName() != null) {
            user.setName(profileRequest.getName());
        }

        if (profileRequest.getEmail() != null) {
            user.setEmail(profileRequest.getEmail());
        }
        if (profileRequest.getPhoneNumber() != null) {
            user.setPhoneNumber(profileRequest.getPhoneNumber());
        }if (profileRequest.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(profileRequest.getPassword()));

        }if (profileRequest.getHeight() != null) {
            user.setHeight(profileRequest.getHeight());
        }if (profileRequest.getCurrentWeight() != null) {
            user.setCurrentWeight(profileRequest.getCurrentWeight());
        }if (profileRequest.getTargetWeight() != null) {
            user.setTargetWeight(profileRequest.getTargetWeight());
        }if (profileRequest.getBirthDay() != null) {
            user.setBirthDay(profileRequest.getBirthDay());
        }if (profileRequest.getProfilePicture() != null) {
            user.setProfilePicture(profileRequest.getProfilePicture());
        }
        UserEntity updatedUser = userRepository.save(user);
        return getUserProfileResponse(updatedUser);
    }
}
