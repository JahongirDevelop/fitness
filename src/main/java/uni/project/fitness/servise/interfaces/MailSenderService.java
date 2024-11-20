package uni.project.fitness.servise.interfaces;

public interface MailSenderService {
    String sendVerificationCode(String email);
    void sendPasswordResetEmail(String email, String token);
}
