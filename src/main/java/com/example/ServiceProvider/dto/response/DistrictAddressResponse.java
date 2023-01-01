package com.example.ServiceProvider.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictAddressResponse {
    private String uuid;
    private String districtName;
    private DivisionAddressResponse divisionAddressResponse;
}
