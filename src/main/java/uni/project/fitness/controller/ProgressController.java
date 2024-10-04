package uni.project.fitness.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.fitness.dto.request.ProgressCreateDTO;
import uni.project.fitness.dto.response.ProgressResponseDTO;
import uni.project.fitness.servise.interfaces.ProgressService;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {
    private final ProgressService progressService;
    @GetMapping
    public ResponseEntity<List<ProgressResponseDTO>> getAllProgresses() {
        return new ResponseEntity<>(progressService.getAllProgresses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgressResponseDTO> getProgressById(@PathVariable UUID id) {
        return new ResponseEntity<>(progressService.getProgressById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ProgressResponseDTO> createProgress(@RequestBody ProgressCreateDTO progressCreateDTO) {
        return new ResponseEntity<>(progressService.createProgress(progressCreateDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgressResponseDTO> updateProgress(@PathVariable UUID id, @RequestBody ProgressCreateDTO progressCreateDTO) {
        return new ResponseEntity<>(progressService.updateProgress(id, progressCreateDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgress(@PathVariable UUID id) {
        progressService.deleteProgress(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/get-user-progresses/{userId}")
    public ResponseEntity<List<ProgressResponseDTO>> getProgressByUserId(@PathVariable UUID userId) {
        List<ProgressResponseDTO> progressDTOList = progressService.getProgressByUserId(userId);
        return new ResponseEntity<>(progressDTOList, HttpStatus.OK);
    }
}

