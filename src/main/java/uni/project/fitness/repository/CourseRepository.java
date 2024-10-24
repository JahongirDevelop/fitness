package uni.project.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.project.fitness.entity.Category;
import uni.project.fitness.entity.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public interface CourseRepository extends JpaRepository<Course, UUID> {
    List<Course> findByCategory(Category category);
}
