package uni.project.fitness.servise.interfaces;

import uni.project.fitness.dto.request.NutritionRequestDTO;
import uni.project.fitness.dto.response.NutritionResponseDTO;
import java.util.List;
import java.util.UUID;
public interface NutritionService {
    NutritionResponseDTO createNutrition(NutritionRequestDTO requestDTO);
    NutritionResponseDTO getNutritionById(UUID id);
    List<NutritionResponseDTO> getAllNutrition(UUID userId);
    NutritionResponseDTO updateNutrition(UUID id, NutritionRequestDTO requestDTO);
    void deleteNutrition(UUID id);
}
