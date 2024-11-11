package uni.project.fitness.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import java.util.List;
@Entity(name = "progress")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Progress extends BaseEntity {
    @ElementCollection
    private List<String> pictures;
    private Integer chest;
    private Integer hips;
    private Integer waist;
    private Integer weight;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
