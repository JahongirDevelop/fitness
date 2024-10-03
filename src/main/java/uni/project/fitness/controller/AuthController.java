package uni.project.fitness.controller;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.fitness.config.security.JwtResponse;
import uni.project.fitness.dto.auth.AuthDto;
import uni.project.fitness.dto.auth.SignUp;
import uni.project.fitness.exception.ErrorDTO;
import uni.project.fitness.servise.interfaces.AuthService;
@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
    private final AuthService authService;

    @PermitAll
    @PostMapping("/sign-up")
    public ResponseEntity<ErrorDTO> signUp(@RequestBody SignUp signUp) {
        return ResponseEntity.status(200).body(authService.addUser(signUp));
    }

    @PermitAll
    @PostMapping("/sign-in")
    public JwtResponse signIn(@Valid @RequestBody AuthDto dto) {
        return authService.signIn(dto);
    }

    @PermitAll
    @PostMapping("/refresh-token")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        return authService.refreshToken(refreshToken);
    }
}

