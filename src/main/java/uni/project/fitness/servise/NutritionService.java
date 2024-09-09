package uni.project.fitness.servise;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.request.NutritionRequestDTO;
import uni.project.fitness.dto.response.NutritionResponseDTO;
import uni.project.fitness.entity.Nutrition;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.repository.NutritionRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NutritionService {

    private final NutritionRepository nutritionRepository;
    private final ModelMapper modelMapper;

    // Create a new nutrition entry
    public NutritionResponseDTO createNutrition(NutritionRequestDTO requestDTO) {
        Nutrition nutrition = modelMapper.map(requestDTO, Nutrition.class);
        Nutrition savedNutrition = nutritionRepository.save(nutrition);
        NutritionResponseDTO responseDTO = modelMapper.map(savedNutrition, NutritionResponseDTO.class);
        responseDTO.setId(savedNutrition.getId());
        return responseDTO;
    }

    // Get a nutrition entry by ID
    public NutritionResponseDTO getNutritionById(UUID id) {
        Nutrition nutrition = nutritionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Nutrition not found with ID: " + id));
        return modelMapper.map(nutrition, NutritionResponseDTO.class);
    }

    // Get all nutrition entries
    public List<NutritionResponseDTO> getAllNutritions() {
        return nutritionRepository.findAll().stream()
                .map(nutrition -> {
                    // Map the Nutrition entity to the DTO
                    NutritionResponseDTO dto = modelMapper.map(nutrition, NutritionResponseDTO.class);
                    // Set description directly as it is now a single text field
                    dto.setDescription(nutrition.getDescription());
                    return dto;
                })
                .collect(Collectors.toList());
    }



    // Update an existing nutrition entry
    public NutritionResponseDTO updateNutrition(UUID id, NutritionRequestDTO requestDTO) {
        Nutrition existingNutrition = nutritionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Nutrition not found with ID: " + id));

        // Update fields
        existingNutrition.setTitle(requestDTO.getTitle());
        existingNutrition.setSubTitle(requestDTO.getSubTitle());
        existingNutrition.setImage(requestDTO.getImage());
        existingNutrition.setDescription(requestDTO.getDescription());

        Nutrition updatedNutrition = nutritionRepository.save(existingNutrition);
        return modelMapper.map(updatedNutrition, NutritionResponseDTO.class);
    }

    // Delete a nutrition entry by ID
    public void deleteNutrition(UUID id) {
        if (!nutritionRepository.existsById(id)) {
            throw new DataNotFoundException("Nutrition not found with ID: " + id);
        }
        nutritionRepository.deleteById(id);
    }
}

