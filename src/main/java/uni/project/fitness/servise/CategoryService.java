package uni.project.fitness.servise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.request.CategoryRequestDTO;
import uni.project.fitness.dto.response.*;
import uni.project.fitness.entity.*;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.repository.CategoryRepository;
import uni.project.fitness.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public String createCategory(CategoryRequestDTO requestDTO) {
        Category category = Category.builder()
                .name(requestDTO.getName())
                .build();
        categoryRepository.save(category);
        return "Successfully created";
    }

    public CategoryResponseDTO updateCategory(UUID id, CategoryRequestDTO requestDTO) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Category not found"));
        category.setName(requestDTO.getName());
        category = categoryRepository.save(category);
        return convertToResponseDTO(category);
    }

    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }

    public CategoryResponseDTO getCategoryById(UUID id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Category not found"));
        return convertToResponseDTO(category);
    }

    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private CategoryResponseDTO convertToResponseDTO(Category category) {
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .courses(category.getCourses().stream()
                        .map(this::convertCourseToResponseDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    private CourseResponseDTO convertCourseToResponseDTO(Course course) {
        return CourseResponseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .subTitle(course.getSubTitle())
                .description(course.getDescription())
                .image(course.getImage())
                .trailerVideo(course.getTrailerVideo())
                .price(course.getPrice())
                .whatYouWillGet(course.getWhatYouWillGet())
                .whatToExpects(course.getWhatToExpects())
                .purpose(course.getPurpose())
                .results(course.getResults())
                .category(convertToCategoryDTO(course.getCategory()))
                .trainings(course.getTrainings().stream()
                        .map(this::convertTrainingDTO)
                        .collect(Collectors.toList()))
                .build();
    }
    private TrainingDTO convertTrainingDTO(Training training){
        return TrainingDTO.builder()
                .description(training.getDescription())
                .id(training.getId())
                .image(training.getImage())
                .title(training.getTitle())
                .build();
    }

    private CategoryDTO convertToCategoryDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public List<CategoryResponseDTOForUser> getAllCategoriesForUser(UUID userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(category -> {
                    List<UserCourseResponseDTO> userCourses = category.getCourses().stream()
                            .map(course -> {
                                UserCourseResponseDTO userCourseDTO = convertToUserCourseResponseDTO(course);
                                userCourseDTO.setIsAccessible(course.isAccessibleForUser(user));
                                return userCourseDTO;
                            })
                            .collect(Collectors.toList());

                    return CategoryResponseDTOForUser.builder()
                            .id(category.getId())
                            .name(category.getName())
                            .courses(userCourses)
                            .build();
                })
                .collect(Collectors.toList());
    }

    public UserCourseResponseDTO  convertToUserCourseResponseDTO (Course course) {
        return UserCourseResponseDTO .builder()
                .id(course.getId())
                .title(course.getTitle())
                .subTitle(course.getSubTitle())
                .description(course.getDescription())
                .image(course.getImage())
                .trailerVideo(course.getTrailerVideo())
                .price(course.getPrice())
                .whatYouWillGet(course.getWhatYouWillGet())
                .whatToExpects(course.getWhatToExpects())
                .purpose(course.getPurpose())
                .results(course.getResults())
                .category(convertToCategoryDTO(course.getCategory()))
                .trainings(course.getTrainings() != null ?
                        course.getTrainings().stream()
                                .map(this::convertTrainingDTO)
                                .collect(Collectors.toList())
                        : new ArrayList<>())
                .build();
    }
}
