package uni.project.fitness.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.fitness.entity.News;
import uni.project.fitness.servise.NewsService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
//@CrossOrigin(origins = "https://4311-93-188-80-101.ngrok-free.app")
@CrossOrigin(origins = "http://127.0.0.1:4040 ") // Replace with your frontend's URL

public class NewsController {
    private final NewsService newsService;

    @GetMapping("/get-all-news")
    public List<News> getAllNews() {
        return newsService.findAll();
    }

    @GetMapping("/get-news-by-id/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable UUID id) {
        Optional<News> news = newsService.findById(id);
        return news.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create-news")
    public News createNews(@RequestBody News news) {
        return newsService.save(news);
    }

    @PutMapping("/update-news//{id}")
    public ResponseEntity<News> updateNews(@PathVariable UUID id, @RequestBody News updatedNews) {
        return ResponseEntity.ok(newsService.update(id, updatedNews));
    }

    @DeleteMapping("/delete-news//{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable UUID id) {
        newsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
