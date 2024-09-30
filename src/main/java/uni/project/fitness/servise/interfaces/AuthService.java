package uni.project.fitness.servise.interfaces;

import uni.project.fitness.config.security.JwtResponse;
import uni.project.fitness.dto.auth.AuthDto;
import uni.project.fitness.dto.auth.SignUp;
import uni.project.fitness.exception.ErrorDTO;

public interface AuthService {
    ErrorDTO addUser(SignUp signUp);
    JwtResponse signIn(AuthDto dto);
    JwtResponse refreshToken(String refreshToken);
}
