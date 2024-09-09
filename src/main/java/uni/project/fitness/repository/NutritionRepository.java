package uni.project.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.project.fitness.entity.Nutrition;

import java.util.UUID;

public interface NutritionRepository extends JpaRepository<Nutrition, UUID> {
}
