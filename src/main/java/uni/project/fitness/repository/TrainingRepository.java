package uni.project.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.project.fitness.entity.Course;
import uni.project.fitness.entity.Teacher;
import uni.project.fitness.entity.Training;
import java.util.List;
import java.util.UUID;
public interface TrainingRepository extends JpaRepository<Training, UUID> {
    List<Training> findByCourse(Course course);

    List<Training> findByTeacher(Teacher teacher);
}

