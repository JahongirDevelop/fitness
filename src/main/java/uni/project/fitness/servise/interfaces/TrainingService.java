package uni.project.fitness.servise.interfaces;

import uni.project.fitness.dto.request.*;
import uni.project.fitness.dto.response.*;
import java.util.List;
import java.util.UUID;
public interface TrainingService {
    TrainingResponseDTO createTraining(TrainingRequestDTO requestDTO);
    TrainingResponseDTO updateTraining(UUID id, TrainingRequestDTO requestDTO);
    void deleteTraining(UUID id);
    TrainingResponseDTO getTrainingById(UUID id);
    List<TrainingResponseDTO> getAllTrainings();
    List<TrainingResponseDTO> getTrainingsByCourse(UUID courseId);
    List<TrainingResponseDTO> getTrainingsByTeacher(UUID teacherId);
}

