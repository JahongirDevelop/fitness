package uni.project.fitness.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.fitness.dto.request.NewsRequestDTO;
import uni.project.fitness.dto.response.NewsResponseDTO;
import uni.project.fitness.servise.interfaces.NewsService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/get-all-news")
    public List<NewsResponseDTO> getAllNews() {
        return newsService.findAll();
    }

    @GetMapping("/get-news-by-id/{id}")
    public ResponseEntity<NewsResponseDTO> getNewsById(@PathVariable UUID id) {
        return newsService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create-news")
    public ResponseEntity<NewsResponseDTO> createNews(@RequestBody NewsRequestDTO newsRequest) {
        NewsResponseDTO createdNews = newsService.save(newsRequest);
        return ResponseEntity.ok(createdNews);
    }

    @PutMapping("/update-news/{id}")
    public ResponseEntity<NewsResponseDTO> updateNews(@PathVariable UUID id, @RequestBody NewsRequestDTO newsRequest) {
        NewsResponseDTO updatedNews = newsService.update(id, newsRequest);
        return ResponseEntity.ok(updatedNews);
    }

    @DeleteMapping("/delete-news/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable UUID id) {
        newsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
