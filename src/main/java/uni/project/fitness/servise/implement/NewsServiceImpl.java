package uni.project.fitness.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.project.fitness.config.mapper.MyConverter;
import uni.project.fitness.dto.request.NewsRequestDTO;
import uni.project.fitness.dto.response.NewsResponseDTO;
import uni.project.fitness.entity.News;
import uni.project.fitness.exception.*;
import uni.project.fitness.repository.NewsRepository;
import uni.project.fitness.servise.interfaces.NewsService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final MyConverter converter;
    @Override
    public List<NewsResponseDTO> findAll() {
        return newsRepository.findAll().stream()
                .map(converter::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<NewsResponseDTO> findById(UUID id) {
        return newsRepository.findById(id)
                .map(converter::convertToResponseDTO);
    }
    @Override
    public NewsResponseDTO save(NewsRequestDTO newsRequest) {
        News news = convertToEntity(newsRequest);
        News savedNews = newsRepository.save(news);
        return converter.convertToResponseDTO(savedNews);
    }
    @Override
    public NewsResponseDTO update(UUID id, NewsRequestDTO newsRequest) {
        News updatedNews = convertToEntity(newsRequest);
        return newsRepository.findById(id)
                .map(news -> {
                    news.setTitle(updatedNews.getTitle());
                    news.setSubTitle(updatedNews.getSubTitle());
                    news.setDescription(updatedNews.getDescription());
                    news.setMedia(updatedNews.getMedia());
                    return newsRepository.save(news);
                })
                .map(converter::convertToResponseDTO)
                .orElseThrow(() -> new DataNotFoundException("News not found with id " + id));
    }
    @Override
    public void deleteById(UUID id) {
        newsRepository.deleteById(id);
    }


    private News convertToEntity(NewsRequestDTO dto) {
        return News.builder()
                .title(dto.getTitle())
                .subTitle(dto.getSubTitle())
                .description(dto.getDescription())
                .media(dto.getMedia())
                .build();
    }
}
