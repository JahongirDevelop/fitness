package uni.project.fitness.servise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.request.CourseRequestDTO;
import uni.project.fitness.dto.response.*;
import uni.project.fitness.entity.Category;
import uni.project.fitness.entity.Course;
import uni.project.fitness.entity.Teacher;
import uni.project.fitness.entity.Training;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.repository.CategoryRepository;
import uni.project.fitness.repository.CourseRepository;
import uni.project.fitness.repository.TeacherRepository;
import uni.project.fitness.repository.TrainingRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final TeacherRepository teacherRepository;
    private final TrainingRepository trainingRepository;

    public CourseResponseDTO createCourse(CourseRequestDTO requestDTO) {
        Course course = Course.builder()
                .title(requestDTO.getTitle())
                .subTitle(requestDTO.getSubTitle())
                .description(requestDTO.getDescription())
                .image(requestDTO.getImage())
                .trailerVideo(requestDTO.getTrailerVideo())
                .price(requestDTO.getPrice())
                .whatToExpects(requestDTO.getWhatToExpects())
                .purpose(requestDTO.getPurpose())
                .icons(requestDTO.getIcons())
                .results(requestDTO.getResults())
                .category(categoryRepository.findById(requestDTO.getCategoryId()).orElseThrow(() -> new DataNotFoundException("Category not found")))
                .build();
        course = courseRepository.save(course);
        return convertToResponseDTO(course);
    }

    public CourseResponseDTO updateCourse(UUID id, CourseRequestDTO requestDTO) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Course not found"));
        course.setTitle(requestDTO.getTitle());
        course.setSubTitle(requestDTO.getSubTitle());
        course.setDescription(requestDTO.getDescription());
        course.setImage(requestDTO.getImage());
        course.setTrailerVideo(requestDTO.getTrailerVideo());
        course.setPrice(requestDTO.getPrice());
        course.setWhatToExpects(requestDTO.getWhatToExpects());
        course.setPurpose(requestDTO.getPurpose());
        course.setIcons(requestDTO.getIcons());
        course.setResults(requestDTO.getResults());
        course.setCategory(categoryRepository.findById(requestDTO.getCategoryId()).orElseThrow(() -> new DataNotFoundException("Category not found")));
        course = courseRepository.save(course);
        return convertToResponseDTO(course);
    }

    public void deleteCourse(UUID id) {
        courseRepository.deleteById(id);
    }

    public CourseResponseDTO getCourseById(UUID id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Course not found"));
        return convertToResponseDTO(course);
    }

    public List<CourseResponseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private CourseResponseDTO convertToResponseDTO(Course course) {
        return CourseResponseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .subTitle(course.getSubTitle())
                .description(course.getDescription())
                .image(course.getImage())
                .trailerVideo(course.getTrailerVideo())
                .price(course.getPrice())
                .whatToExpects(course.getWhatToExpects())
                .purpose(course.getPurpose())
                .icons(course.getIcons())
                .results(course.getResults())
                .category(convertToCategoryDTO(course.getCategory()))
                .trainings(Collections.singletonList(TrainingDTO.builder().id(course.getId()).title(course.getTitle()).build()))
//                .trainings(course.getTrainings() != null ?
//                        course.getTrainings().stream()
//                                .map(this::convertTrainingToResponseDTO)
//                                .collect(Collectors.toList())
//                        : new ArrayList<>())
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
                .build();
    }

    private TeacherResponseDTO convertTeacherToResponseDTO(Teacher teacher) {
        return TeacherResponseDTO.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .image(teacher.getImage())
                .workType(teacher.getWorkType())
                .about(teacher.getAbout())
                .build();
    }

    private CategoryDTO convertToCategoryDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}