package com.example.ServiceProvider.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest {
    private String serviceName;
    private Double price;
    private Integer rating;

    private String subCategoryUuid;
    private String districtAddressUuid;
}
