package uni.project.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.project.fitness.entity.Category;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findByParentCategoryIsNull();
    Optional<List<Category>> findAllByParentCategory_Id(UUID categoryId);

}
