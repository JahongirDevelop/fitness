package uni.project.fitness.entity;
import org.hibernate.annotations.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @CreationTimestamp
    protected LocalDateTime createdDate;

//    @UpdateTimestamp
//    protected LocalDateTime updatedDate;

}
