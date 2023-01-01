package com.example.ServiceProvider.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryResponse {

    private String uuid;
    private String subCategoryName;
    private CategoryResponse categoryResponse;
}
