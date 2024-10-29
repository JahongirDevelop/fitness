package uni.project.fitness.servise.interfaces;

import uni.project.fitness.dto.response.*;
import java.util.List;
import java.util.UUID;
public interface CompletedCourseService {
    List<CompletedCourseDTO> getCompletedCoursesByUserId(UUID userId);
    CompletedCourseResponseDTO createCompletedCourse(CompletedCourseRequestDTO requestDTO);
    List<CompletedCourseResponseDTO> getAllCompletedCourses();
    CompletedCourseResponseDTO getCompletedCourseById(UUID id);
    CompletedCourseResponseDTO updateCompletedCourse(UUID id, CompletedCourseRequestDTO requestDTO);
    void deleteCompletedCourse(UUID id);
}
