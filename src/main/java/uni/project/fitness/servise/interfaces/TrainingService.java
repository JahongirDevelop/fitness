package uni.project.fitness.servise.interfaces;

import uni.project.fitness.dto.request.*;
import uni.project.fitness.dto.response.*;
import java.util.List;
import java.util.UUID;
public interface TrainingService {
    TrainingForCourseResponseDTO createTrainingForCourse(TrainingForCourseRequestDTO requestDTO);
    TrainingForTeacherResponseDTO createTrainingForTeacher(TrainingForTeacherRequestDTO requestDTO);
    TrainingForCourseResponseDTO updateTrainingForCourse(UUID id, TrainingForCourseRequestDTO requestDTO);
    TrainingForTeacherResponseDTO updateTrainingForTeacher(UUID id, TrainingForTeacherRequestDTO requestDTO);
    void deleteTraining(UUID id);
    TrainingResponseDTO getTrainingById(UUID id);
    List<TrainingResponseDTO> getAllTrainings();
    List<TrainingForCourseResponseDTO> getTrainingsByCourse(UUID courseId);
    List<TrainingForTeacherResponseDTO> getTrainingsByTeacher(UUID teacherId,UUID userId);
}

