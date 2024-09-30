package uni.project.fitness.servise.interfaces;

import uni.project.fitness.dto.request.ProgressCreateDTO;
import uni.project.fitness.dto.response.ProgressResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProgressService {
    List<ProgressResponseDTO> getAllProgresses();
    ProgressResponseDTO getProgressById(UUID id);
    ProgressResponseDTO createProgress(ProgressCreateDTO progressCreateDTO);
    ProgressResponseDTO updateProgress(UUID id, ProgressCreateDTO progressCreateDTO);
    void deleteProgress(UUID id);
    List<ProgressResponseDTO> getProgressByUserId(UUID userId);
}
