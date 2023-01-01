package com.example.ServiceProvider.service;

import com.example.ServiceProvider.dto.request.ServiceRequest;
import com.example.ServiceProvider.dto.response.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ServiceService {
    ServiceResponse createService(ServiceRequest serviceRequest);


    List<ServiceResponse> getServiceList();

    ServiceResponse editService(String uuid, ServiceRequest serviceRequest);

    boolean deleteService(String uuid);
}
