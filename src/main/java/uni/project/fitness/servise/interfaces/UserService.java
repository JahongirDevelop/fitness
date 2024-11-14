package uni.project.fitness.servise.interfaces;

import uni.project.fitness.dto.response.UserResponse;
import uni.project.fitness.entity.UserEntity;
import uni.project.fitness.entity.enums.UserRole;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface UserService {
    Optional<UserResponse> findById(UUID id);
    Optional<UserEntity> findByEmail(String email);
    String deleteUser(UUID id);
    UserResponse updateUserRole(UUID userId, UserRole role);
    List<UserResponse> getAll();
}
