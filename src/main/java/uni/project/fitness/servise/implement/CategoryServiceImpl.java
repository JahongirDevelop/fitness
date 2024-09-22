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
    public CategoryDTO createCategory(CategoryRequestDTO requestDTO) {
        Category category = Category.builder()
                .name(requestDTO.getName())
                .build();
        categoryRepository.save(category);
        return converter.convertToCategoryDTO(category);
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
}

