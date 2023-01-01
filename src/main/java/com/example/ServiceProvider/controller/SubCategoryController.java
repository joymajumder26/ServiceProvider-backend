package com.example.ServiceProvider.controller;

import com.example.ServiceProvider.dto.request.CategoryRequest;
import com.example.ServiceProvider.dto.request.SubCategoryRequest;
import com.example.ServiceProvider.dto.response.CategoryResponse;
import com.example.ServiceProvider.dto.response.SubCategoryResponse;
import com.example.ServiceProvider.service.SubCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/subCategories")
public class SubCategoryController {

    private SubCategoryService subCategoryService;

    @PostMapping
    public SubCategoryResponse createSubCategory(@RequestBody SubCategoryRequest subCategoryRequest){
        return subCategoryService.createSubCategory(subCategoryRequest);
    }
    @GetMapping
    public List<SubCategoryResponse> getSubCategoryList(){
        return subCategoryService.getSubCategoryList();
    }


    @PutMapping("/{subCategory-uuid}")
    public SubCategoryResponse editSubCategory(@PathVariable("subCategory-uuid") String uuid,
                                         @RequestBody SubCategoryRequest subCategoryRequest){
        return subCategoryService.editCategory(uuid, subCategoryRequest);
    }

    @DeleteMapping("/{subCategory-uuid}")
    public boolean deleteSubCategory(@PathVariable("subCategory-uuid") String uuid) {
        return subCategoryService.deleteCategory(uuid);
    }

    @GetMapping("/{subCategory-uuid}")
    public SubCategoryResponse getSubCategoryByUuid(@PathVariable("subCategory-uuid") String uuid) {
        return subCategoryService.getSubCategoryByUuid(uuid);
    }


}
