package uni.project.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.project.fitness.entity.Progress;
import uni.project.fitness.entity.UserEntity;
import java.util.List;
import java.util.UUID;
@Repository
public interface ProgressRepository extends JpaRepository<Progress, UUID> {
    List<Progress> findByUser(UserEntity user);
}

