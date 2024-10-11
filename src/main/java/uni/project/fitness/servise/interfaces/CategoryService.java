package uni.project.fitness.servise.interfaces;

import uni.project.fitness.dto.request.*;
import uni.project.fitness.dto.response.*;
import java.util.List;
import java.util.UUID;
public interface CategoryService {
    TopCategoryResponseDTO createTopCategory(TopCategoryRequestDTO requestDTO);
    TopCategoryResponseDTO updateTopCategory(UUID id, TopCategoryRequestDTO requestDTO);

    SubCategoryResponseDTO createSubCategory(SubCategoryRequestDTO requestDTO);
    SubCategoryResponseDTO updateSubCategory(UUID id, SubCategoryRequestDTO requestDTO);
    void deleteCategory(UUID id);
    List<TopCategoryResponseDTO> getTopLevelCategories();

    List<SubCategoryResponseDTO> getSubcategories(UUID topCategoryId, UUID userId);
    // Fetch courses for a selected subcategory
    List<CourseResponseDTO> getCoursesForSubcategory(UUID subcategoryId);
}

