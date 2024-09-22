package uni.project.fitness.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.response.CompletedCourseDTO;
import uni.project.fitness.dto.response.CompletedCourseRequestDTO;
import uni.project.fitness.dto.response.CompletedCourseResponseDTO;
import uni.project.fitness.entity.CompletedCourse;
import uni.project.fitness.entity.Course;
import uni.project.fitness.entity.UserEntity;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.config.mapper.MyConverter;
import uni.project.fitness.repository.CompletedCourseRepository;
import uni.project.fitness.repository.CourseRepository;
import uni.project.fitness.repository.UserRepository;
import uni.project.fitness.servise.interfaces.CompletedCourseService;
import uni.project.fitness.servise.interfaces.CourseService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class CompletedCourseServiceImpl implements CompletedCourseService {

    private final CompletedCourseRepository completedCourseRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseService courseService;
    private final MyConverter converter;

    @Override
    public List<CompletedCourseDTO> getCompletedCoursesByUserId(UUID userId) {
        List<CompletedCourse> completedCourses = completedCourseRepository.findByUserId(userId);
        return completedCourses.stream()
                .map(completedCourse -> converter.convertToCompletedCourseDTO(completedCourse, courseService))
                .collect(Collectors.toList());
    }

    @Override
    public CompletedCourseResponseDTO createCompletedCourse(CompletedCourseRequestDTO requestDTO) {
        Course course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new DataNotFoundException("Course not found"));
        UserEntity author = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        if (completedCourseRepository.existsByUserIdAndCourseId(requestDTO.getUserId(), requestDTO.getCourseId())) {
            throw new IllegalArgumentException("User has already completed this course");
        }

        CompletedCourse completedCourse = CompletedCourse.builder()
                .course(course)
                .user(author)
                .build();
        CompletedCourse savedCompletedCourse = completedCourseRepository.save(completedCourse);
        return converter.convertToCompletedCourseResponseDTO(savedCompletedCourse);
    }

    @Override
    public List<CompletedCourseResponseDTO> getAllCompletedCourses() {
        return completedCourseRepository.findAll().stream()
                .map(converter::convertToCompletedCourseResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CompletedCourseResponseDTO getCompletedCourseById(UUID id) {
        CompletedCourse completedCourse = completedCourseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CompletedCourse not found with id: " + id));
        return converter.convertToCompletedCourseResponseDTO(completedCourse);
    }

    @Override
    public CompletedCourseResponseDTO updateCompletedCourse(UUID id, CompletedCourseRequestDTO requestDTO) {
        Course course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new DataNotFoundException("Course not found"));
        UserEntity author = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        CompletedCourse existingCompletedCourse = completedCourseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CompletedCourse not found with id: " + id));

        existingCompletedCourse.setCourse(course);
        existingCompletedCourse.setUser(author);

        CompletedCourse updatedCompletedCourse = completedCourseRepository.save(existingCompletedCourse);
        return converter.convertToCompletedCourseResponseDTO(updatedCompletedCourse);
    }

    @Override
    public void deleteCompletedCourse(UUID id) {
        CompletedCourse existingCompletedCourse = completedCourseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CompletedCourse not found with id: " + id));
        completedCourseRepository.delete(existingCompletedCourse);
    }
}
