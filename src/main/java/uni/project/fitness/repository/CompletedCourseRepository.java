package uni.project.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.project.fitness.entity.CompletedCourse;
import java.util.List;
import java.util.UUID;
@Repository
public interface CompletedCourseRepository extends JpaRepository<CompletedCourse, UUID> {
    List<CompletedCourse> findByUserId(UUID userId);
    boolean existsByUserIdAndCourseId(UUID userId, UUID courseId);
}
