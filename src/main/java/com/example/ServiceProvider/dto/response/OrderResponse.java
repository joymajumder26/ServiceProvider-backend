package com.example.ServiceProvider.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String uuid;
    private String orderName;
    private ServiceResponse serviceResponse;
    private SubCategoryResponse subCategoryResponse;
    private DistrictAddressResponse districtAddressResponse;

}
