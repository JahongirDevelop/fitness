package uni.project.fitness.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.fitness.dto.request.CategoryRequestDTO;
import uni.project.fitness.dto.response.CategoryDTO;
import uni.project.fitness.dto.response.CategoryResponseDTO;
//import uni.project.fitness.servise.CategoryService;
import uni.project.fitness.dto.response.CategoryResponseDTOForUser;
import uni.project.fitness.servise.interfaces.CategoryService;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryRequestDTO requestDTO) {
        return ResponseEntity.ok(categoryService.createCategory(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable UUID id, @RequestBody CategoryRequestDTO requestDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @GetMapping("/get-all-categories-for-user/{userId}")
    public ResponseEntity<List<CategoryResponseDTOForUser>> getAllCoursesForUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(categoryService.getAllCategoriesForUser(userId));
    }
}
