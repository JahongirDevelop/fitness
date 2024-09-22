package uni.project.fitness.servise.interfaces;

import uni.project.fitness.dto.request.*;
import uni.project.fitness.dto.response.*;
import java.util.List;
import java.util.UUID;
public interface CategoryService {
    CategoryDTO createCategory(CategoryRequestDTO requestDTO);
    CategoryResponseDTO updateCategory(UUID id, CategoryRequestDTO requestDTO);
    void deleteCategory(UUID id);
    CategoryResponseDTO getCategoryById(UUID id);
    List<CategoryResponseDTO> getAllCategories();
    List<CategoryResponseDTOForUser> getAllCategoriesForUser(UUID userId);
}

