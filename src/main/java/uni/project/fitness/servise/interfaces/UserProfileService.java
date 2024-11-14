package uni.project.fitness.servise.interfaces;

import uni.project.fitness.dto.request.UserProfileRequest;
import uni.project.fitness.dto.response.UserProfileResponse;

import java.util.UUID;
public interface UserProfileService {
    UserProfileResponse getUserProfile(UUID userId);
    UserProfileResponse updateUserProfile(UUID userId, UserProfileRequest profileRequest);

}
