package uni.project.fitness.config.AA;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.repository.UserRepository;

/**
 * Implementation of the UserDetailsService interface for loading user details based on the username.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;
    @Override
    @Transactional()
    public UserDetails loadUserByUsername(String username) {
        return repository.findByEmail(username)
                .orElseThrow(() -> new DataNotFoundException("User not found"));
    }
}
