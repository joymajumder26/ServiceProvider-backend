package com.example.ServiceProvider.dto.request;

import com.example.ServiceProvider.dto.response.OrderResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String orderName;
    private String serviceUuid;
    private String subCategoryUuid;
    private String districtAddressUuid;


}
