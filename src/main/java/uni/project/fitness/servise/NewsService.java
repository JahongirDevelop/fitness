package uni.project.fitness.servise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.request.NewsRequestDTO;
import uni.project.fitness.dto.response.CategoryDTO;
import uni.project.fitness.dto.response.NewsResponseDTO;
import uni.project.fitness.entity.News;
import uni.project.fitness.exception.*;
import uni.project.fitness.repository.CategoryRepository;
import uni.project.fitness.repository.NewsRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;

    public List<NewsResponseDTO> findAll() {
        return newsRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public Optional<NewsResponseDTO> findById(UUID id) {
        return newsRepository.findById(id)
                .map(this::convertToResponseDTO);
    }

    public NewsResponseDTO save(NewsRequestDTO newsRequest) {
        News news = convertToEntity(newsRequest);
        News savedNews = newsRepository.save(news);
        return convertToResponseDTO(savedNews);
    }

    public NewsResponseDTO update(UUID id, NewsRequestDTO newsRequest) {
        News updatedNews = convertToEntity(newsRequest);
        return newsRepository.findById(id)
                .map(news -> {
                    news.setTitle(updatedNews.getTitle());
                    news.setSubTitle(updatedNews.getSubTitle());
                    news.setDescription(updatedNews.getDescription());
//                    news.setCategory(updatedNews.getCategory()); // Ensure this is correctly handled
                    news.setMedia(updatedNews.getMedia());
                    return newsRepository.save(news);
                })
                .map(this::convertToResponseDTO)
                .orElseThrow(() -> new DataNotFoundException("News not found with id " + id));
    }

    public void deleteById(UUID id) {
        newsRepository.deleteById(id);
    }

    private NewsResponseDTO convertToResponseDTO(News news) {
        return new NewsResponseDTO(
                news.getId(),
                news.getTitle(),
                news.getSubTitle(),
                news.getDescription(),
                news.getMedia()
//                new CategoryDTO(
//                        news.getCategory().getId(),
//                        news.getCategory().getName()
//                )
        );
    }

    private News convertToEntity(NewsRequestDTO dto) {
        return News.builder()
                .title(dto.getTitle())
                .subTitle(dto.getSubTitle())
                .description(dto.getDescription())
                .media(dto.getMedia())
//                .category(categoryRepository.findById(dto.getCategoryId())
//                        .orElseThrow(() -> new DataNotFoundException("Category not found with id " + dto.getCategoryId())))
                .build();
    }
}
