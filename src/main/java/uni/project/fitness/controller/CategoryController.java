package uni.project.fitness.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.project.fitness.dto.request.SubCategoryRequestDTO;
import uni.project.fitness.dto.request.TopCategoryRequestDTO;
import uni.project.fitness.dto.response.*;
import uni.project.fitness.servise.interfaces.CategoryService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    // Create Top Category
    @PostMapping("/create-topCategory")
    public ResponseEntity<TopCategoryResponseDTO> createTopCategory(@RequestBody TopCategoryRequestDTO requestDTO) {
        TopCategoryResponseDTO response = categoryService.createTopCategory(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Update Top Category
    @PutMapping("/update-topCategory/{id}")
    public ResponseEntity<TopCategoryResponseDTO> updateTopCategory(@PathVariable UUID id, @RequestBody TopCategoryRequestDTO requestDTO) {
        TopCategoryResponseDTO response = categoryService.updateTopCategory(id, requestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Create Sub Category
    @PostMapping("/create-subCategory")
    public ResponseEntity<SubCategoryResponseDTO> createSubCategory(@RequestBody SubCategoryRequestDTO requestDTO) {
        SubCategoryResponseDTO response = categoryService.createSubCategory(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Update Sub Category
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

    @GetMapping("/get-all-categories-for-user/{userId}")
    public ResponseEntity<List<CategoryResponseDTOForUser>> getAllCoursesForUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(categoryService.getAllCategoriesForUser(userId));
    }
    @GetMapping("/top")
    public List<TopCategoryResponseDTO> getTopLevelCategories() {
        return categoryService.getTopLevelCategories();
    }

    @GetMapping("/{topCategoryId}/subcategories")
    public List<SubCategoryResponseDTO> getSubcategories(@PathVariable UUID topCategoryId) {
        return categoryService.getSubcategories(topCategoryId);
    }

    // Endpoint to fetch courses of a selected subcategory
    @GetMapping("/subcategories/{subcategoryId}/courses")
    public List<CourseResponseDTO> getCoursesForSubcategory(@PathVariable UUID subcategoryId) {
        return categoryService.getCoursesForSubcategory(subcategoryId);
    }

}
