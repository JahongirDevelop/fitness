package uni.project.fitness.servise.implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.request.NutritionRequestDTO;
import uni.project.fitness.dto.response.NutritionResponseDTO;
import uni.project.fitness.entity.Nutrition;
import uni.project.fitness.entity.UserEntity;
import uni.project.fitness.entity.enums.SubscriptionPeriod;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.repository.NutritionRepository;
import uni.project.fitness.repository.UserRepository;
import uni.project.fitness.servise.interfaces.NutritionService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NutritionServiceImpl implements NutritionService {

    private final NutritionRepository nutritionRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    @Override
    public NutritionResponseDTO createNutrition(NutritionRequestDTO requestDTO) {
        Nutrition nutrition = modelMapper.map(requestDTO, Nutrition.class);
        Nutrition savedNutrition = nutritionRepository.save(nutrition);
        NutritionResponseDTO responseDTO = modelMapper.map(savedNutrition, NutritionResponseDTO.class);
        responseDTO.setId(savedNutrition.getId());
        return responseDTO;
    }
    @Override
    public NutritionResponseDTO getNutritionById(UUID id) {
        Nutrition nutrition = nutritionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Nutrition not found with ID: " + id));
        return modelMapper.map(nutrition, NutritionResponseDTO.class);
    }
    @Override
    public List<NutritionResponseDTO> getAllNutrition(UUID userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found!"));
        boolean isAvailable = (user.getSubscription().getPeriod()!=SubscriptionPeriod.ONE_MONTH_BASIC)&&(user.getSubscription().getPeriod()!=null);
        return nutritionRepository.findAll().stream()
                .map(nutrition -> {
                    NutritionResponseDTO dto = modelMapper.map(nutrition, NutritionResponseDTO.class);
                    dto.setDescription(nutrition.getDescription());
                    dto.setPdfLink(nutrition.getPdfLink());
                    dto.setIsAvailable(isAvailable);
                    return dto;
                })
                .collect(Collectors.toList());
    }


    @Override
    public NutritionResponseDTO updateNutrition(UUID id, NutritionRequestDTO requestDTO) {
        Nutrition existingNutrition = nutritionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Nutrition not found with ID: " + id));

        existingNutrition.setTitle(requestDTO.getTitle());
        existingNutrition.setSubTitle(requestDTO.getSubTitle());
        existingNutrition.setImage(requestDTO.getImage());
        existingNutrition.setDescription(requestDTO.getDescription());
        existingNutrition.setPdfLink(requestDTO.getPdfLink());

        Nutrition updatedNutrition = nutritionRepository.save(existingNutrition);
        return modelMapper.map(updatedNutrition, NutritionResponseDTO.class);
    }
    @Override
    public void deleteNutrition(UUID id) {
        if (!nutritionRepository.existsById(id)) {
            throw new DataNotFoundException("Nutrition not found with ID: " + id);
        }
        nutritionRepository.deleteById(id);
    }
}

