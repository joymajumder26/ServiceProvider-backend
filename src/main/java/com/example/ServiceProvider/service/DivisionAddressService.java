package com.example.ServiceProvider.service;

import com.example.ServiceProvider.dto.request.DivisionAddressRequest;
import com.example.ServiceProvider.dto.response.DivisionAddressResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DivisionAddressService {
    DivisionAddressResponse createDivision(DivisionAddressRequest divisionAddressRequest);

    List<DivisionAddressResponse> getDivisionList();

    DivisionAddressResponse editDivision(String uuid, DivisionAddressRequest divisionAddressRequest);

    boolean deleteDivision(String uuid);
}
