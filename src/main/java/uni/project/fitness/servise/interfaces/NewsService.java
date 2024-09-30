package uni.project.fitness.servise.interfaces;

import uni.project.fitness.dto.request.NewsRequestDTO;
import uni.project.fitness.dto.response.NewsResponseDTO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NewsService {
    List<NewsResponseDTO> findAll();
    Optional<NewsResponseDTO> findById(UUID id);
    NewsResponseDTO save(NewsRequestDTO newsRequest);
    NewsResponseDTO update(UUID id, NewsRequestDTO newsRequest);
    void deleteById(UUID id);
}
