package uni.project.fitness.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.request.CourseRequestDTO;
import uni.project.fitness.dto.response.*;
import uni.project.fitness.entity.*;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.config.mapper.MyConverter;
import uni.project.fitness.repository.*;
import uni.project.fitness.servise.interfaces.CourseService;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final MyConverter converter;

    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO requestDTO) {
        Course course = Course.builder()
                .title(requestDTO.getTitle())
                .subTitle(requestDTO.getSubTitle())
                .description(requestDTO.getDescription())
                .image(requestDTO.getImage())
                .trailerVideo(requestDTO.getTrailerVideo())
                .price(requestDTO.getPrice())
                .whatYouWillGet(requestDTO.getWhatYouWillGet())
                .whatToExpects(requestDTO.getWhatToExpects())
                .purpose(requestDTO.getPurpose())
                .results(requestDTO.getResults())
                .category(categoryRepository.findById(requestDTO.getCategoryId())
                        .orElseThrow(() -> new DataNotFoundException("Category not found")))
                .build();
        course = courseRepository.save(course);
        return converter.convertToResponseDTO(course);
    }

    @Override
    public CourseResponseDTO updateCourse(UUID id, CourseRequestDTO requestDTO) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Course not found"));
        course.setTitle(requestDTO.getTitle());
        course.setSubTitle(requestDTO.getSubTitle());
        course.setDescription(requestDTO.getDescription());
        course.setImage(requestDTO.getImage());
        course.setTrailerVideo(requestDTO.getTrailerVideo());
        course.setPrice(requestDTO.getPrice());
        course.setWhatYouWillGet(requestDTO.getWhatYouWillGet());
        course.setWhatToExpects(requestDTO.getWhatToExpects());
        course.setPurpose(requestDTO.getPurpose());
        course.setResults(requestDTO.getResults());
        course.setCategory(categoryRepository.findById(requestDTO.getCategoryId())
                .orElseThrow(() -> new DataNotFoundException("Category not found")));
        course = courseRepository.save(course);
        return converter.convertToResponseDTO(course);
    }

    @Override
    public void deleteCourse(UUID id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseResponseDTO getCourseById(UUID id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Course not found"));
        return converter.convertToResponseDTO(course);
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(converter::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponseDTO> getCoursesByCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new DataNotFoundException("Category not found with id: " + categoryId));
        return courseRepository.findByCategory(category).stream()
                .map(converter::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseTrailerDTO> getAllCourseIdsAndTrailerImages() {
        return courseRepository.findAll().stream()
                .map(course -> new CourseTrailerDTO(
                        course.getId(),
                        course.getImage()
                ))
                .collect(Collectors.toList());
    }
}

