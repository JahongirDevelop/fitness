package uni.project.fitness.servise.implement;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uni.project.fitness.config.security.JwtResponse;
import uni.project.fitness.config.security.JwtService;
import uni.project.fitness.dto.auth.SignUp;
import uni.project.fitness.dto.auth.AuthDto;
import uni.project.fitness.entity.UserEntity;
import uni.project.fitness.exception.DataAlreadyExistsException;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.exception.ErrorDTO;
import uni.project.fitness.repository.UserRepository;
import uni.project.fitness.entity.enums.UserRole;
import uni.project.fitness.servise.interfaces.AuthService;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ErrorDTO addUser(SignUp signUp) {
        if (userRepository.existsByEmail(signUp.getEmail())) {
            throw new DataAlreadyExistsException("This email already exists: " + signUp.getEmail());
        }

        UserEntity user = modelMapper.map(signUp, UserEntity.class);
        user.setPassword(passwordEncoder.encode(signUp.getPassword()));
        user.setRole(UserRole.USER);
        userRepository.save(user);

        return new ErrorDTO("User successfully registered", 200);
    }
    @Override
    public JwtResponse signIn(AuthDto dto) {
        UserEntity user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            return new JwtResponse(accessToken, refreshToken);
        } else {
            throw new AuthenticationCredentialsNotFoundException("Invalid credentials");
        }
    }
    @Override
    public JwtResponse refreshToken(String refreshToken) {
        Jws<Claims> claimsJws = jwtService.extractToken(refreshToken);
        String userId = claimsJws.getBody().getSubject();
        UserEntity user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        String newAccessToken = jwtService.generateAccessToken(user);
        String newRefreshToken = jwtService.generateRefreshToken(user);
        return new JwtResponse(newAccessToken, newRefreshToken);
    }
}
