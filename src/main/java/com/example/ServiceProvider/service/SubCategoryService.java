package com.example.ServiceProvider.service;

import com.example.ServiceProvider.dto.request.SubCategoryRequest;
import com.example.ServiceProvider.dto.response.SubCategoryResponse;

import java.util.List;

public interface SubCategoryService {

    SubCategoryResponse createSubCategory(SubCategoryRequest subCategoryRequest);

    List<SubCategoryResponse> getSubCategoryList();

    SubCategoryResponse editCategory(String uuid, SubCategoryRequest subCategoryRequest);

    boolean deleteCategory(String uuid);

    SubCategoryResponse getSubCategoryByUuid(String uuid);
}
