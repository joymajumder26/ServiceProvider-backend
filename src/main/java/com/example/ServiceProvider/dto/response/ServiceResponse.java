package com.example.ServiceProvider.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse {

    private String uuid;
    private String serviceName;
    private Double price;
    private Integer rating;
    private SubCategoryResponse subCategoryResponse;
    private DistrictAddressResponse districtAddressResponse;

}
