package uni.project.fitness.servise;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.project.fitness.entity.News;
import uni.project.fitness.exception.*;
import uni.project.fitness.repository.NewsRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public Optional<News> findById(UUID id) {
        return newsRepository.findById(id);
    }

    public News save(News news) {
        return newsRepository.save(news);
    }

    public News update(UUID id, News updatedNews) {
        return newsRepository.findById(id).map(news -> {
            news.setTitle(updatedNews.getTitle());
            news.setSubTitle(updatedNews.getSubTitle());
            news.setDescription(updatedNews.getDescription());
            news.setCategory(updatedNews.getCategory());
            news.setImage(updatedNews.getImage());
            return newsRepository.save(news);
        }).orElseThrow(() -> new DataNotFoundException("News not found with id " + id));
    }

    public void deleteById(UUID id) {
        newsRepository.deleteById(id);
    }

}
