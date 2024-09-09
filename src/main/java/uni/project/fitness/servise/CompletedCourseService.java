package uni.project.fitness.servise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.response.CompletedCourseDTO;
import uni.project.fitness.dto.response.CompletedCourseRequestDTO;
import uni.project.fitness.dto.response.CompletedCourseResponseDTO;
import uni.project.fitness.dto.response.CourseResponseDTO;
import uni.project.fitness.entity.CompletedCourse;
import uni.project.fitness.entity.Course;
import uni.project.fitness.entity.UserEntity;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.repository.CompletedCourseRepository;
import uni.project.fitness.repository.CourseRepository;
import uni.project.fitness.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompletedCourseService {

    private final CompletedCourseRepository completedCourseRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseService courseService;
    public List<CompletedCourseDTO> getCompletedCoursesByUserId(UUID userId) {
        // Fetch completed courses by user ID from the repository
        List<CompletedCourse> completedCourses = completedCourseRepository.findByUserId(userId);

        // Map each completed course to a CompletedCourseDTO
        return completedCourses.stream()
                .map(completedCourse -> {
                    // Convert the Course entity to CourseResponseDTO
                    CourseResponseDTO courseResponseDTO = courseService.convertToResponseDTO(completedCourse.getCourse());

                    // Create and return a CompletedCourseDTO object
                    return CompletedCourseDTO.builder()
                            .courseResponseDTO(courseResponseDTO)
                            .build();
                })
                .collect(Collectors.toList());
    }

    public CompletedCourseResponseDTO createCompletedCourse(CompletedCourseRequestDTO requestDTO) {
        Course course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new DataNotFoundException("Course not found"));
        UserEntity author = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        if (completedCourseRepository.existsByUserIdAndCourseId(requestDTO.getUserId(), requestDTO.getCourseId())) {
            throw new IllegalArgumentException("User has already completed this course");
        }


        CompletedCourse completedCourse = CompletedCourse.builder()
                .course(course)  // Assuming Course has a constructor with ID
                .user(author)  // Assuming UserEntity has a constructor with ID
                .build();
        CompletedCourse savedCompletedCourse = completedCourseRepository.save(completedCourse);
        return mapToResponseDTO(savedCompletedCourse);
    }

    public List<CompletedCourseResponseDTO> getAllCompletedCourses() {
        return completedCourseRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public CompletedCourseResponseDTO getCompletedCourseById(UUID id) {
        CompletedCourse completedCourse = completedCourseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CompletedCourse not found with id: " + id));
        return mapToResponseDTO(completedCourse);
    }

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
        return mapToResponseDTO(updatedCompletedCourse);
    }

    public void deleteCompletedCourse(UUID id) {
        CompletedCourse existingCompletedCourse = completedCourseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CompletedCourse not found with id: " + id));
        completedCourseRepository.delete(existingCompletedCourse);
    }

    private CompletedCourseResponseDTO mapToResponseDTO(CompletedCourse completedCourse) {
        return CompletedCourseResponseDTO.builder()
                .id(completedCourse.getId())
                .courseId(completedCourse.getCourse().getId())
                .userId(completedCourse.getUser().getId())
                .build();
    }

}

