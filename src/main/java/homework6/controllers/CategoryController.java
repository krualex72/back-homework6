package homework6.controllers;

import homework6.dto.CategoryDto;
import homework6.entites.Category;
import homework6.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import homework6.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getOneById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find category with id: " + id));
        return new CategoryDto(category);
    }
}
