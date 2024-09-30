package uni.project.fitness.servise.implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.response.UserResponse;
import uni.project.fitness.entity.UserEntity;
import uni.project.fitness.entity.enums.UserRole;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.repository.UserRepository;
import uni.project.fitness.servise.interfaces.UserService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public Optional<UserResponse> findById(UUID id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
        userResponse.setId(userEntity.get().getId());
        userResponse.setRole(userEntity.get().getRole());
        return Optional.of(userResponse);
    }
    @Override
    public String deleteUser(UUID id) {
        userRepository.deleteById(id);
        return "Successfully deleted";
    }
    @Override
    public UserResponse updateUserRole(UUID userId, UserRole role) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        user.setRole(role);
        UserEntity updatedUserEntity = userRepository.save(user);
        UserResponse map = modelMapper.map(updatedUserEntity, UserResponse.class);
        map.setId(updatedUserEntity.getId());
        map.setRole(updatedUserEntity.getRole());
        return map;
    }
    @Override
    public List<UserResponse> getAll() {
        List<UserResponse> userResponses = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
            userResponse.setId(userEntity.getId());
            userResponse.setRole(userEntity.getRole());
            userResponses.add(userResponse);
        }
        return userResponses;
    }
}
