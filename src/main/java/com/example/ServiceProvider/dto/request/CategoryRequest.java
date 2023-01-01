package com.example.ServiceProvider.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    private String categoryName;
    private String createBy;
    private LocalDateTime createdOn;
    private String lastUpdatedBy;
}
