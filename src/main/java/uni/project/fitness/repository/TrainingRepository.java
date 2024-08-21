package uni.project.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.project.fitness.entity.Training;

import java.util.UUID;

public interface TrainingRepository extends JpaRepository<Training, UUID> {
}

