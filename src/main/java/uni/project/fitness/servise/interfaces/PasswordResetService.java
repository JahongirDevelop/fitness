package uni.project.fitness.servise.interfaces;


import uni.project.fitness.entity.UserEntity;

public interface PasswordResetService {


    void sendPasswordResetLink(String email);
    UserEntity findUser(String code);
    void resetPassword(String code, String newPassword);


}
