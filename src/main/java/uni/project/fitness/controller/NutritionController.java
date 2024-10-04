package uni.project.fitness.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.fitness.dto.request.NutritionRequestDTO;
import uni.project.fitness.dto.response.NutritionResponseDTO;
import uni.project.fitness.servise.interfaces.NutritionService;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/nutritions")
@RequiredArgsConstructor
public class NutritionController {

    private final NutritionService nutritionService;
    @PostMapping
    public ResponseEntity<NutritionResponseDTO> createNutrition(@RequestBody NutritionRequestDTO requestDTO) {
        return ResponseEntity.ok().body(nutritionService.createNutrition(requestDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<NutritionResponseDTO> getNutritionById(@PathVariable UUID id) {
        NutritionResponseDTO responseDTO = nutritionService.getNutritionById(id);
        return ResponseEntity.ok(responseDTO);
    }
    @GetMapping
    public ResponseEntity<List<NutritionResponseDTO>> getAllNutrition() {
        List<NutritionResponseDTO> responseDTOs = nutritionService.getAllNutrition();
        return ResponseEntity.ok(responseDTOs);
    }
    @PutMapping("/{id}")
    public ResponseEntity<NutritionResponseDTO> updateNutrition(@PathVariable UUID id, @RequestBody NutritionRequestDTO requestDTO) {
        NutritionResponseDTO responseDTO = nutritionService.updateNutrition(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNutrition(@PathVariable UUID id) {
        nutritionService.deleteNutrition(id);
        return ResponseEntity.noContent().build();
    }
}

