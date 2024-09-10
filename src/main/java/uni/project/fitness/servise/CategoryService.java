package uni.project.fitness.servise;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.request.CategoryRequestDTO;
import uni.project.fitness.dto.response.*;
import uni.project.fitness.entity.Category;
import uni.project.fitness.entity.Course;
import uni.project.fitness.entity.Teacher;
import uni.project.fitness.entity.Training;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

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
        // Implement similar conversion logic for Course
        // Assuming CourseResponseDTO is already implemented
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
//                .icons(course.getIcons())
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
    private TrainingResponseDTO convertTrainingToResponseDTO(Training training) {
        return TrainingResponseDTO.builder()
                .id(training.getId())
                .title(training.getTitle())
                .teacher(convertTeacherToResponseDTO(training.getTeacher()))
                .description(training.getDescription())
                .shortDescription(training.getShortDescription())
                .image(training.getImage())
                .video(training.getVideo())
                .importantInfo(training.getImportantInfo())
                .equipments(training.getEquipments())
                .musclesInvolved(training.getMusclesInvolved())
                .results(training.getResults())
                .course(CourseDTO.builder().id(training.getCourse().getId()).title(training.getCourse().getTitle()).build())
                .build();
    }
    private TeacherResponseDTO convertTeacherToResponseDTO(Teacher teacher) {
        return TeacherResponseDTO.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .image(teacher.getImage())
                .workType(teacher.getWorkType())
                .about(teacher.getAbout())
//                .trainings(teacher.getTrainingList() != null
//                        ? teacher.getTrainingList().stream()
//                        .map(Training::getId)
//                        .collect(Collectors.toList())
//                        : new ArrayList<>()) // Handle case where trainings might be null
                .build();
    }


}
