package com.example.ServiceProvider.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    private String uuid;

    private String categoryName;
    private String createBy;
    private LocalDateTime createdOn;
    private String lastUpdatedBy;
}
