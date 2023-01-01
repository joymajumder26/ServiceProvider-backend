package com.example.ServiceProvider.service.impl;

import com.example.ServiceProvider.dto.request.CategoryRequest;
import com.example.ServiceProvider.dto.response.CategoryResponse;
import com.example.ServiceProvider.model.CategoryModel;
import com.example.ServiceProvider.repository.CategoryRepository;
import com.example.ServiceProvider.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        CategoryModel categoryModel = new CategoryModel(categoryRequest.getCategoryName());
        categoryModel.setCreatedBy("Admin");
        categoryModel.setCreatedOn(LocalDateTime.now());
        categoryModel.setUuid(UUID.randomUUID().toString());
        categoryModel.setLastUpdatedBy(categoryRequest.getLastUpdatedBy());

        categoryModel = categoryRepository.save(categoryModel);

        CategoryResponse categoryResponse =
                new CategoryResponse(categoryModel.getUuid(), categoryModel.getCategoryName(),categoryModel.getCreatedBy(),categoryModel.getCreatedOn(),categoryModel.getLastUpdatedBy());

        return categoryResponse;
    }

    @Override
    public List<CategoryResponse> getCategoryList() {
        List<CategoryModel> categoryModels = categoryRepository.findAll();

        List<CategoryResponse> categoryResponses = new ArrayList<>();

        for (CategoryModel categoryModel: categoryModels) {
            CategoryResponse categoryResponse =
                    new CategoryResponse(categoryModel.getUuid(), categoryModel.getCategoryName(),categoryModel.getCreatedBy(),categoryModel.getCreatedOn(),categoryModel.getLastUpdatedBy());
            categoryResponses.add(categoryResponse);
        }

        return categoryResponses;
    }

    @Override
    public CategoryResponse editCategory(String uuid, CategoryRequest categoryRequest) {
        Optional<CategoryModel> categoryModelOptional = categoryRepository.findByUuid(uuid);

        if(categoryModelOptional.isPresent()){
            CategoryModel categoryModel = categoryModelOptional.get();

            categoryModel.setCategoryName(categoryRequest.getCategoryName());

            categoryModel = categoryRepository.save(categoryModel);

            return new CategoryResponse(categoryModel.getUuid(), categoryModel.getCategoryName(),categoryModel.getCreatedBy(),categoryModel.getCreatedOn(),categoryModel.getLastUpdatedBy());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with uuid: " + uuid + " is not found.");
        }
    }

    @Override
    public boolean deleteCategory(String uuid) {
        Optional<CategoryModel> categoryModelOptional = categoryRepository.findByUuid(uuid);

        if(categoryModelOptional.isPresent()){
            CategoryModel categoryModel = categoryModelOptional.get();

            categoryRepository.delete(categoryModel);

            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with uuid: " + uuid + " is not found.");
        }
    }

    @Override
    public CategoryResponse getCategoryByUuid(String uuid) {
        Optional<CategoryModel> categoryModelOptional = categoryRepository.findByUuid(uuid);

        if(categoryModelOptional.isPresent()){
            CategoryModel categoryModel = categoryModelOptional.get();

            return new CategoryResponse(categoryModel.getUuid(), categoryModel.getCategoryName(),categoryModel.getCreatedBy(),categoryModel.getCreatedOn(),categoryModel.getLastUpdatedBy());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with uuid: " + uuid + " is not found.");
        }
    }

    @Override
    public List<CategoryResponse> filterCategories(Integer pageNo, Integer pageSize, String uuid, String categoryName) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "id"));

        CategoryModel exampleCategory = new CategoryModel();
        exampleCategory.setCategoryName(categoryName);
        exampleCategory.setUuid(uuid);

        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("categoryName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("uuid", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());


        Page<CategoryModel> categoryModelPage = categoryRepository.findAll(Example.of(exampleCategory, matcher), pageable);



        List<CategoryResponse> categoryResponses = new ArrayList<>();

        for (CategoryModel categoryModel: categoryModelPage.getContent()) {
            CategoryResponse categoryResponse =
                    new CategoryResponse(categoryModel.getUuid(), categoryModel.getCategoryName(),categoryModel.getCreatedBy(),categoryModel.getCreatedOn(),categoryModel.getLastUpdatedBy());
            categoryResponses.add(categoryResponse);
        }

        return categoryResponses;
    }
}
