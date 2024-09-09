package uni.project.fitness.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.fitness.dto.request.TrainingRequestDTO;
import uni.project.fitness.dto.response.TrainingResponseDTO;
import uni.project.fitness.servise.TrainingService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/trainings")
@RequiredArgsConstructor
public class TrainingController {
    private final TrainingService trainingService;

    @PostMapping
    public ResponseEntity<TrainingResponseDTO> createTraining(@RequestBody TrainingRequestDTO trainingRequest) {
        TrainingResponseDTO createdTraining = trainingService.createTraining(trainingRequest);
        return ResponseEntity.ok(createdTraining);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingResponseDTO> updateTraining(@PathVariable UUID id, @RequestBody TrainingRequestDTO trainingRequest) {
        TrainingResponseDTO updatedTraining = trainingService.updateTraining(id, trainingRequest);
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
    public ResponseEntity<List<TrainingResponseDTO>> getTrainingsByCourse(@PathVariable UUID courseId) {
        return ResponseEntity.ok(trainingService.getTrainingsByCourse(courseId));
    }
    @GetMapping("/get-all-by-teacher/{teacherId}")
    public ResponseEntity<List<TrainingResponseDTO>> getTrainingsByTeacher(@PathVariable UUID teacherId) {
        return ResponseEntity.ok(trainingService.getTrainingsByTeacher(teacherId));
    }

}
