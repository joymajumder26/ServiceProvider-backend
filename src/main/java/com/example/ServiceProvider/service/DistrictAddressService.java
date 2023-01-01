package com.example.ServiceProvider.service;

import com.example.ServiceProvider.dto.request.DistrictAddressRequest;
import com.example.ServiceProvider.dto.response.DistrictAddressResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DistrictAddressService {
    DistrictAddressResponse createDistrict(DistrictAddressRequest districtAddressRequest);

    List<DistrictAddressResponse> getDistrictList();

    DistrictAddressResponse editDistrict(String uuid, DistrictAddressRequest districtAddressRequest);

    boolean deleteDistrict(String uuid);
}
