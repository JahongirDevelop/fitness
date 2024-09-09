package uni.project.fitness.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.fitness.dto.request.NutritionRequestDTO;
import uni.project.fitness.dto.response.NutritionResponseDTO;
import uni.project.fitness.servise.NutritionService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/nutritions")
@RequiredArgsConstructor
public class NutritionController {

    private final NutritionService nutritionService;

    // Create a new nutrition entry
    @PostMapping
    public ResponseEntity<NutritionResponseDTO> createNutrition(@RequestBody NutritionRequestDTO requestDTO) {
        return ResponseEntity.ok().body(nutritionService.createNutrition(requestDTO));
    }

    // Get a nutrition entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<NutritionResponseDTO> getNutritionById(@PathVariable UUID id) {
        NutritionResponseDTO responseDTO = nutritionService.getNutritionById(id);
        return ResponseEntity.ok(responseDTO);
    }

    // Get all nutrition entries
    @GetMapping
    public ResponseEntity<List<NutritionResponseDTO>> getAllNutritions() {
        List<NutritionResponseDTO> responseDTOs = nutritionService.getAllNutritions();
        return ResponseEntity.ok(responseDTOs);
    }

    // Update an existing nutrition entry
    @PutMapping("/{id}")
    public ResponseEntity<NutritionResponseDTO> updateNutrition(@PathVariable UUID id, @RequestBody NutritionRequestDTO requestDTO) {
        NutritionResponseDTO responseDTO = nutritionService.updateNutrition(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Delete a nutrition entry by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNutrition(@PathVariable UUID id) {
        nutritionService.deleteNutrition(id);
        return ResponseEntity.noContent().build();
    }
}

