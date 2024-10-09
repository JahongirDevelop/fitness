package uni.project.fitness.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.request.SubCategoryRequestDTO;
import uni.project.fitness.dto.request.TopCategoryRequestDTO;
import uni.project.fitness.dto.response.*;
import uni.project.fitness.entity.*;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.config.mapper.MyConverter;
import uni.project.fitness.repository.CategoryRepository;
import uni.project.fitness.repository.UserRepository;
import uni.project.fitness.servise.interfaces.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final MyConverter converter;

    @Override
    public TopCategoryResponseDTO createTopCategory(TopCategoryRequestDTO requestDTO) {
        // Create top-level category
        Category category = Category.builder()
                .name(requestDTO.getName())
                .parentCategory(null) // Top-level categories have no parent.
                .subcategories(new ArrayList<>()) // Empty list for subcategories.
                .build();

        Category savedCategory = categoryRepository.save(category);
        return converter.convertToTopCategoryResponseDTO(savedCategory);
    }

    @Override
    public TopCategoryResponseDTO updateTopCategory(UUID id, TopCategoryRequestDTO requestDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Top Category not found"));

        category.setName(requestDTO.getName());
        category = categoryRepository.save(category);
        return converter.convertToTopCategoryResponseDTO(category);
    }

    @Override
    public SubCategoryResponseDTO createSubCategory(SubCategoryRequestDTO requestDTO) {
        // Fetch and validate the parent category (Top Category)
        Category parentCategory = categoryRepository.findById(requestDTO.getParentCategoryId())
                .orElseThrow(() -> new DataNotFoundException("Top Category not found"));

        // Create a new subcategory under the specified top category
        Category subCategory = Category.builder()
                .name(requestDTO.getName())
                .parentCategory(parentCategory)
                .subcategories(new ArrayList<>()) // Empty list for sub-subcategories (if needed)
                .courses(new ArrayList<>()) // Empty list for courses
                .image(requestDTO.getImage()) // Set the image for subcategories
                .build();

        Category savedSubCategory = categoryRepository.save(subCategory);
        return converter.convertToSubCategoryResponseDTO(savedSubCategory);
    }

    @Override
    public SubCategoryResponseDTO updateSubCategory(UUID id, SubCategoryRequestDTO requestDTO) {
        Category subCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Subcategory not found"));

        subCategory.setName(requestDTO.getName());
        subCategory.setImage(requestDTO.getImage());
        subCategory = categoryRepository.save(subCategory);
        return converter.convertToSubCategoryResponseDTO(subCategory);
    }

    @Override
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
    @Override
    public List<CategoryResponseDTOForUser> getAllCategoriesForUser(UUID userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> converter.convertToResponseDTOForUser(category, user))
                .collect(Collectors.toList());
    }
    // Fetch all top-level categories
    @Override
    public List<TopCategoryResponseDTO> getTopLevelCategories() {
        List<Category> topLevelCategories = categoryRepository.findByParentCategoryIsNull();
        return topLevelCategories.stream()
                .map(converter::convertToTopCategoryResponseDTO)
                .collect(Collectors.toList());
    }

    // Fetch subcategories for a selected top category
    @Override
    public List<SubCategoryResponseDTO> getSubcategories(UUID topCategoryId) {
        Category topCategory = categoryRepository.findById(topCategoryId)
                .orElseThrow(() -> new DataNotFoundException("Top category not found"));

        return topCategory.getSubcategories().stream()
                .map(converter::convertToSubCategoryResponseDTO)
                .collect(Collectors.toList());
    }

    // Fetch courses for a selected subcategory
    @Override
    public List<CourseResponseDTO> getCoursesForSubcategory(UUID subcategoryId) {
        Category subcategory = categoryRepository.findById(subcategoryId)
                .orElseThrow(() -> new DataNotFoundException("Subcategory not found"));

        return subcategory.getCourses().stream()
                .map(converter::convertToResponseDTO)
                .collect(Collectors.toList());
    }
}

