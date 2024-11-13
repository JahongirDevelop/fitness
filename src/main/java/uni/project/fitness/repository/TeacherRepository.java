package uni.project.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.project.fitness.entity.Teacher;
import java.util.UUID;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
}

