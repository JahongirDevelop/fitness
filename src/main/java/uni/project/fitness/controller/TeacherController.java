package uni.project.fitness.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.fitness.dto.request.TeacherRequestDTO;
import uni.project.fitness.dto.response.TeacherResponseDTO;
import uni.project.fitness.servise.interfaces.TeacherService;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherResponseDTO> createTeacher(@RequestBody TeacherRequestDTO requestDTO) {
        return ResponseEntity.ok(teacherService.createTeacher(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(@PathVariable UUID id, @RequestBody TeacherRequestDTO requestDTO) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, requestDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable UUID id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> getTeacherById(@PathVariable UUID id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @GetMapping
    public ResponseEntity<List<TeacherResponseDTO>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }
}
