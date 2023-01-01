package com.example.ServiceProvider.controller;

import com.example.ServiceProvider.dto.request.CategoryRequest;
import com.example.ServiceProvider.dto.response.CategoryResponse;
import com.example.ServiceProvider.service.CategoryService;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    @GetMapping
    public List<CategoryResponse> getCategoryList(){
        return categoryService.getCategoryList();
    }

    @PutMapping("/{category-uuid}")
    public CategoryResponse editCategory(@PathVariable("category-uuid") String uuid,
                                         @RequestBody CategoryRequest categoryRequest){
        return categoryService.editCategory(uuid, categoryRequest);
    }

    @DeleteMapping("/{category-uuid}")
    public boolean deleteCategory(@PathVariable("category-uuid") String uuid) {
        return categoryService.deleteCategory(uuid);
    }

    @GetMapping("/{category-uuid}")
    public CategoryResponse getCategoryByUuid(@PathVariable("category-uuid") String uuid) {
        return categoryService.getCategoryByUuid(uuid);
    }

    @GetMapping("/filter")
    public List<CategoryResponse> filterCategories(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String uuid,
            @RequestParam(required = false) String categoryName
            ){
        return categoryService.filterCategories(pageNo, pageSize, uuid, categoryName);
    }

}
