package uni.project.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.project.fitness.entity.Category;
import uni.project.fitness.entity.Course;
import java.util.List;
import java.util.UUID;
@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    List<Course> findByCategory(Category category);
}
