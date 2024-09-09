package uni.project.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.project.fitness.entity.CompletedCourse;
import uni.project.fitness.entity.Course;

import java.util.List;
import java.util.UUID;

public interface CompletedCourseRepository extends JpaRepository<CompletedCourse, UUID> {
    List<CompletedCourse> findByUserId(UUID userId);
    boolean existsByUserIdAndCourseId(UUID userId, UUID courseId);
}
