package uni.project.fitness.servise.interfaces;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.request.UserProfileRequest;
import uni.project.fitness.dto.response.UserProfileResponse;
import uni.project.fitness.entity.UserEntity;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.repository.UserRepository;
import java.util.UUID;
public interface UserProfileService {


    UserProfileResponse getUserProfile(UUID userId);
    UserProfileResponse updateUserProfile(UUID userId, UserProfileRequest profileRequest);

}
