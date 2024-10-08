package uni.project.fitness.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.request.CategoryRequestDTO;
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
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        // Fetch and validate the parent category if provided
        Category parentCategory = (categoryRequestDTO.getParentCategoryId() != null) ?
                categoryRepository.findById(categoryRequestDTO.getParentCategoryId())
                        .orElseThrow(() -> new DataNotFoundException("Parent category not found"))
                : null;

        // Create and save the new category, initializing the subcategories list to avoid null issues
        Category category = Category.builder()
                .name(categoryRequestDTO.getName())
                .parentCategory(parentCategory)
                .subcategories(new ArrayList<>())  // Ensure an empty list instead of null
                .build();

        Category savedCategory = categoryRepository.save(category);

        // Return the created category as a response DTO, including only relevant subcategories
        return convertToResponseDTOWithSubcategories(savedCategory, categoryRequestDTO.getParentCategoryId() == null);
    }



    @Override
    public CategoryResponseDTO updateCategory(UUID id, CategoryRequestDTO requestDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Category not found"));
        category.setName(requestDTO.getName());
        category = categoryRepository.save(category);
        return converter.convertToResponseDTO(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponseDTO getCategoryById(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Category not found"));
        return converter.convertToResponseDTO(category);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(converter::convertToResponseDTO)
                .collect(Collectors.toList());
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
//    @Override
//    public List<CategoryResponseDTO> getTopLevelCategories() {
//        // Fetch top-level categories that have no parent (parentCategory is null)
//        List<Category> topLevelCategories = categoryRepository.findByParentCategoryIsNull();
//
//        // Convert the top-level categories and their nested subcategories into DTOs
//        return topLevelCategories.stream()
//                .map(this::convertToResponseDTOForTopLevel)
//                .collect(Collectors.toList());
//    }
//
//    // Helper method for single-argument mapping of top-level categories
//    private CategoryResponseDTO convertToResponseDTOForTopLevel(Category category) {
//        // Use the main conversion method with `includeSubcategories` set to true for top-level categories
//        return convertToResponseDTOWithSubcategories(category, true);
//    }


    private CategoryResponseDTO convertToResponseDTOWithSubcategories(Category category, boolean includeSubcategories) {
        CategoryResponseDTO responseDTO = new CategoryResponseDTO();
        responseDTO.setId(category.getId());
        responseDTO.setName(category.getName());
        responseDTO.setParentCategoryId(category.getParentCategory() != null ? category.getParentCategory().getId() : null);

        // Handle subcategories to avoid null values
        List<Category> subcategories = category.getSubcategories() != null ? category.getSubcategories() : new ArrayList<>();
        if (includeSubcategories) {
            responseDTO.setSubcategories(subcategories.stream()
                    .map(subcategory -> convertToResponseDTOWithSubcategories(subcategory, false))
                    .collect(Collectors.toList()));
        } else {
            responseDTO.setSubcategories(new ArrayList<>());  // Set to an empty list when subcategories are not included
        }

        // Convert the List<Course> to List<CourseResponseDTO>
        responseDTO.setCourses(category.getCourses() != null ?
                category.getCourses().stream()
                        .map(converter::convertToResponseDTO)
                        .collect(Collectors.toList())
                : new ArrayList<>());

        return responseDTO;
    }



    @Override
    public List<CategoryResponseDTO> getTopLevelCategories() {
        List<Category> topLevelCategories = categoryRepository.findByParentCategoryIsNull();
        return topLevelCategories.stream()
                .map(category -> convertToResponseDTO(category, false))
                .collect(Collectors.toList());
    }
    @Override
    public List<CategoryResponseDTO> getSubcategories(UUID topCategoryId) {
        Category topCategory = categoryRepository.findById(topCategoryId)
                .orElseThrow(() -> new DataNotFoundException("Top category not found"));
        return topCategory.getSubcategories().stream()
                .map(subcategory -> convertToResponseDTO(subcategory, false))
                .collect(Collectors.toList());
    }
    @Override
    public List<CategoryResponseDTO> getSimpleCategories(UUID subcategoryId) {
        Category subcategory = categoryRepository.findById(subcategoryId)
                .orElseThrow(() -> new DataNotFoundException("Subcategory not found"));
        return subcategory.getSubcategories().stream()
                .map(simpleCategory -> convertToResponseDTO(simpleCategory, false))
                .collect(Collectors.toList());
    }
    @Override
    public List<CourseResponseDTO> getCoursesOfSimpleCategory(UUID simpleCategoryId) {
        Category simpleCategory = categoryRepository.findById(simpleCategoryId)
                .orElseThrow(() -> new DataNotFoundException("Simple category not found"));
        return simpleCategory.getCourses().stream()
                .map(converter::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private CategoryResponseDTO convertToResponseDTO(Category category, boolean includeSubcategories) {
        CategoryResponseDTO responseDTO = new CategoryResponseDTO();
        responseDTO.setId(category.getId());
        responseDTO.setName(category.getName());
        responseDTO.setParentCategoryId(category.getParentCategory() != null ? category.getParentCategory().getId() : null);

        // Include subcategories or set to empty list
        if (includeSubcategories) {
            responseDTO.setSubcategories(category.getSubcategories().stream()
                    .map(subcategory -> convertToResponseDTO(subcategory, false))
                    .collect(Collectors.toList()));
        } else {
            responseDTO.setSubcategories(new ArrayList<>());
        }

        // Courses should be empty here as it's only for simple categories
        responseDTO.setCourses(new ArrayList<>());
        return responseDTO;
    }


}

