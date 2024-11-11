package uni.project.fitness.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.*;
import uni.project.fitness.config.mapper.MyConverter;
import uni.project.fitness.dto.request.ProgressCreateDTO;
import uni.project.fitness.dto.response.ProgressResponseDTO;
import uni.project.fitness.entity.Progress;
import uni.project.fitness.entity.UserEntity;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.repository.ProgressRepository;
import uni.project.fitness.repository.UserRepository;
import uni.project.fitness.servise.interfaces.ProgressService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ProgressServiceImpl implements ProgressService {
    private final ProgressRepository progressRepository;
    private final UserRepository userRepository;
    private final MyConverter converter;
    @Override
    public List<ProgressResponseDTO> getAllProgresses() {
        return progressRepository.findAll().stream().map(converter::convertToResponseDTO).collect(Collectors.toList());
    }
    @Override
    public ProgressResponseDTO getProgressById(UUID id) {
        Progress progress = progressRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Progress not found with id: " + id));
        return convertToResponseDTO(progress);
    }
    @Override
    public ProgressResponseDTO createProgress(ProgressCreateDTO progressCreateDTO) {
        UserEntity user = userRepository.findById(progressCreateDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + progressCreateDTO.getUserId()));

        Progress progress = Progress.builder()
                .pictures(progressCreateDTO.getPictures())
                .chest(progressCreateDTO.getChest())
                .hips(progressCreateDTO.getHips())
                .waist(progressCreateDTO.getWaist())
                .weight(progressCreateDTO.getWeight())
                .user(user)
                .build();

        Progress savedProgress = progressRepository.save(progress);
        return convertToResponseDTO(savedProgress);
    }
    @Override
    public ProgressResponseDTO updateProgress(UUID id, ProgressCreateDTO progressCreateDTO) {
        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Progress not found with id: " + id));

        UserEntity user = userRepository.findById(progressCreateDTO.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + progressCreateDTO.getUserId()));

        progress.setPictures(progressCreateDTO.getPictures());
        progress.setChest(progressCreateDTO.getChest());
        progress.setHips(progressCreateDTO.getHips());
        progress.setWaist(progressCreateDTO.getWaist());
        progress.setWeight(progressCreateDTO.getWeight());
        progress.setUser(user);

        Progress updatedProgress = progressRepository.save(progress);
        return convertToResponseDTO(updatedProgress);
    }
    @Override
    public void deleteProgress(UUID id) {
        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Progress not found with id: " + id));
        progressRepository.delete(progress);
    }

    private ProgressResponseDTO convertToResponseDTO(Progress progress) {
        return ProgressResponseDTO.builder()
                .id(progress.getId())
                .pictures(progress.getPictures())
                .chest(progress.getChest())
                .hips(progress.getHips())
                .waist(progress.getWaist())
                .weight(progress.getWeight())
                .userId(progress.getUser().getId())
                .date(progress.getCreatedDate().toLocalDate())
                .build();
    }
    @Override
    public List<ProgressResponseDTO> getProgressByUserId(UUID userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + userId));

        List<Progress> progresses = progressRepository.findByUser(user);

        return progresses.stream().map(converter::convertToResponseDTO).collect(Collectors.toList());
    }


}

