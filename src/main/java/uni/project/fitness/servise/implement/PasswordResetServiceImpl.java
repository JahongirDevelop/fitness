package uni.project.fitness.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.auth.PasswordResetToken;
import uni.project.fitness.entity.UserEntity;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.repository.PasswordResetTokenRepository;
import uni.project.fitness.repository.UserRepository;
import uni.project.fitness.servise.interfaces.MailSenderService;
import uni.project.fitness.servise.interfaces.PasswordResetService;
import java.time.LocalDateTime;
import java.util.Random;
@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {
    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final MailSenderService emailService;
    @Override
    public void sendPasswordResetLink(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        // Generate a password reset token
        Random random = new Random();
        int i = random.nextInt(100000);
        String code = Integer.toString(i);
        PasswordResetToken passwordResetToken = PasswordResetToken.builder()
                .code(code)
                .user(user)
                .expiryDate(LocalDateTime.now().plusSeconds(120))
                .build();
        tokenRepository.save(passwordResetToken);

        // Send email with reset link
        emailService.sendPasswordResetEmail(user.getEmail(), code);
    }
    @Override
    public UserEntity findUser(String code){
        PasswordResetToken passwordResetToken = tokenRepository.findByCode(code)
                .orElseThrow(() -> new DataNotFoundException("Invalid code or User"));
        return passwordResetToken.getUser();
    }
    @Override
    public void resetPassword(String code, String newPassword) {
        UserEntity userEntity = findUser(code);
        PasswordResetToken passwordResetToken = tokenRepository.findByCodeAndUserId(code, userEntity.getId())
                .orElseThrow(() -> new DataNotFoundException("Invalid code or user"));

        if (passwordResetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new DataNotFoundException("Code has expired");
        }

        UserEntity user = passwordResetToken.getUser();
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);
        tokenRepository.delete(passwordResetToken);
    }


}
