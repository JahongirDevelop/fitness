package uni.project.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.project.fitness.entity.Teacher;

import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
}

