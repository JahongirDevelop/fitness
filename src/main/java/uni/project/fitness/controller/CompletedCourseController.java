package uni.project.fitness.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.fitness.dto.response.CompletedCourseRequestDTO;
import uni.project.fitness.dto.response.CompletedCourseResponseDTO;
import uni.project.fitness.servise.interfaces.CompletedCourseService;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/completed-courses")
@RequiredArgsConstructor
public class CompletedCourseController {

    private final CompletedCourseService completedCourseService;

    @PostMapping
    public ResponseEntity<CompletedCourseResponseDTO> createCompletedCourse(@RequestBody CompletedCourseRequestDTO requestDTO) {
        CompletedCourseResponseDTO responseDTO = completedCourseService.createCompletedCourse(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<CompletedCourseResponseDTO>> getAllCompletedCourses() {
        List<CompletedCourseResponseDTO> responseDTOList = completedCourseService.getAllCompletedCourses();
        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompletedCourseResponseDTO> getCompletedCourseById(@PathVariable UUID id) {
        CompletedCourseResponseDTO responseDTO = completedCourseService.getCompletedCourseById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompletedCourseResponseDTO> updateCompletedCourse(@PathVariable UUID id,
                                                                            @RequestBody CompletedCourseRequestDTO requestDTO) {
        CompletedCourseResponseDTO responseDTO = completedCourseService.updateCompletedCourse(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompletedCourse(@PathVariable UUID id) {
        completedCourseService.deleteCompletedCourse(id);
        return ResponseEntity.noContent().build();
    }
}

