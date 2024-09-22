package uni.project.fitness.servise.interfaces;

import uni.project.fitness.dto.request.*;
import uni.project.fitness.dto.response.*;
import java.util.List;
import java.util.UUID;

public interface CourseService {
    CourseResponseDTO createCourse(CourseRequestDTO requestDTO);
    CourseResponseDTO updateCourse(UUID id, CourseRequestDTO requestDTO);
    void deleteCourse(UUID id);
    CourseResponseDTO getCourseById(UUID id);
    List<CourseResponseDTO> getAllCourses();
    List<CourseResponseDTO> getCoursesByCategory(UUID categoryId);
    List<CourseTrailerDTO> getAllCourseIdsAndTrailerImages();
}

