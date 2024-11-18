package uni.project.fitness.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.fitness.dto.request.TrainingForCourseRequestDTO;
import uni.project.fitness.dto.request.TrainingForTeacherRequestDTO;
import uni.project.fitness.dto.response.TrainingForCourseResponseDTO;
import uni.project.fitness.dto.response.TrainingForTeacherResponseDTO;
import uni.project.fitness.dto.response.TrainingResponseDTO;
import uni.project.fitness.servise.interfaces.TrainingService;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/trainings")
@RequiredArgsConstructor
public class TrainingController {
    private final TrainingService trainingService;
    @PostMapping("/create-training-for-course")
    public ResponseEntity<TrainingForCourseResponseDTO> createTrainingForCourse(@RequestBody TrainingForCourseRequestDTO trainingRequest) {
        TrainingForCourseResponseDTO createdTraining = trainingService.createTrainingForCourse(trainingRequest);
        return ResponseEntity.ok(createdTraining);
    }
    @PostMapping("/create-training-for-teacher")
    public ResponseEntity<TrainingForTeacherResponseDTO> createTrainingForTeacher(@RequestBody TrainingForTeacherRequestDTO trainingRequest) {
        TrainingForTeacherResponseDTO createdTraining = trainingService.createTrainingForTeacher(trainingRequest);
        return ResponseEntity.ok(createdTraining);
    }
    @PutMapping("/update-training-by-course/{id}")
    public ResponseEntity<TrainingForCourseResponseDTO> updateTraining(@PathVariable UUID id, @RequestBody TrainingForCourseRequestDTO trainingRequest) {
        TrainingForCourseResponseDTO updatedTraining = trainingService.updateTrainingForCourse(id, trainingRequest);
        return ResponseEntity.ok(updatedTraining);
    }
    @PutMapping("/update-training-by-teacher/{id}")
    public ResponseEntity<TrainingForTeacherResponseDTO> updateTraining(@PathVariable UUID id, @RequestBody TrainingForTeacherRequestDTO trainingRequest) {
        TrainingForTeacherResponseDTO updatedTraining = trainingService.updateTrainingForTeacher(id, trainingRequest);
        return ResponseEntity.ok(updatedTraining);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable UUID id) {
        trainingService.deleteTraining(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<TrainingResponseDTO> getTrainingById(@PathVariable UUID id) {
        TrainingResponseDTO training = trainingService.getTrainingById(id);
        return ResponseEntity.ok(training);
    }
    @GetMapping
    public ResponseEntity<List<TrainingResponseDTO>> getAllTrainings() {
        List<TrainingResponseDTO> trainings = trainingService.getAllTrainings();
        return ResponseEntity.ok(trainings);
    }
    @GetMapping("/get-all-by-course/{courseId}")
    public ResponseEntity<List<TrainingForCourseResponseDTO>> getTrainingsByCourse(@PathVariable UUID courseId) {
        return ResponseEntity.ok(trainingService.getTrainingsByCourse(courseId));
    }
    @GetMapping("/get-all-by-teacher/{teacherId}")
    public ResponseEntity<List<TrainingForTeacherResponseDTO>> getTrainingsByTeacher(Principal principal, @PathVariable UUID teacherId) {
        return ResponseEntity.ok(trainingService.getTrainingsByTeacher(teacherId, UUID.fromString(principal.getName())));
    }
}
