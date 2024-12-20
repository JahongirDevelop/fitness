package uni.project.fitness.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.fitness.dto.request.SubCategoryRequestDTO;
import uni.project.fitness.dto.request.TopCategoryRequestDTO;
import uni.project.fitness.dto.response.*;
import uni.project.fitness.servise.interfaces.CategoryService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create-topCategory")
    public ResponseEntity<TopCategoryResponseDTO> createTopCategory(@RequestBody TopCategoryRequestDTO requestDTO) {
        TopCategoryResponseDTO response = categoryService.createTopCategory(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/update-topCategory/{id}")
    public ResponseEntity<TopCategoryResponseDTO> updateTopCategory(@PathVariable UUID id, @RequestBody TopCategoryRequestDTO requestDTO) {
        TopCategoryResponseDTO response = categoryService.updateTopCategory(id, requestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/create-subCategory")
    public ResponseEntity<SubCategoryResponseDTO> createSubCategory(@RequestBody SubCategoryRequestDTO requestDTO) {
        SubCategoryResponseDTO response = categoryService.createSubCategory(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/update-subCategory/{id}")
    public ResponseEntity<SubCategoryResponseDTO> updateSubCategory(@PathVariable UUID id, @RequestBody SubCategoryRequestDTO requestDTO) {
        SubCategoryResponseDTO response = categoryService.updateSubCategory(id, requestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/top")
    public List<TopCategoryResponseDTO> getTopLevelCategories() {
        return categoryService.getTopLevelCategories();
    }

    @GetMapping("/{topCategoryId}/subcategories")
    public List<SubCategoryResponseDTO> getSubcategories(Principal principal,  @PathVariable UUID topCategoryId) {
        return categoryService.getSubcategories(topCategoryId, UUID.fromString(principal.getName()));
    }

    @GetMapping("/subcategories/{subcategoryId}/courses")
    public List<CourseResponseDTO> getCoursesForSubcategory(@PathVariable UUID subcategoryId) {
        return categoryService.getCoursesForSubcategory(subcategoryId);
    }

}
