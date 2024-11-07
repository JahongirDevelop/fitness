package uni.project.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.project.fitness.entity.Nutrition;
import java.util.UUID;
@Repository
public interface NutritionRepository extends JpaRepository<Nutrition, UUID> {
}
