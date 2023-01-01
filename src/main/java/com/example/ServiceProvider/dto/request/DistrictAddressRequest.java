package com.example.ServiceProvider.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictAddressRequest {
    private String districtName;
    private String divisionUuid;


}
