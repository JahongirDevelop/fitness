package uni.project.fitness.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uni.project.fitness.entity.enums.UserRole;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity extends BaseEntity implements UserDetails {
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private Double height;
    private Double currentWeight;
    private Double targetWeight;
    private LocalDate birthDay;
    private String profilePicture;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
    private Subscription subscription;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>(Set.of(new SimpleGrantedAuthority("ROLE_" + role.name())));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
