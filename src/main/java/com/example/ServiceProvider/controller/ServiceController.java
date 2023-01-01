package com.example.ServiceProvider.controller;


import com.example.ServiceProvider.dto.request.CategoryRequest;
import com.example.ServiceProvider.dto.request.ServiceRequest;
import com.example.ServiceProvider.dto.response.CategoryResponse;
import com.example.ServiceProvider.dto.response.ServiceResponse;
import com.example.ServiceProvider.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private ServiceService serviceService;

    @PostMapping
    public ServiceResponse createService(@RequestBody ServiceRequest serviceRequest) {
        return serviceService.createService(serviceRequest);
    }

    @GetMapping
    public List<ServiceResponse> getServiceList(){
        return serviceService.getServiceList();
    }

    @PutMapping("/{service-uuid}")
    public ServiceResponse editService(@PathVariable("service-uuid") String uuid,
                                         @RequestBody ServiceRequest serviceRequest){
        return serviceService.editService(uuid, serviceRequest);
    }

    @DeleteMapping("/{service-uuid}")
    public boolean deleteService(@PathVariable("service-uuid") String uuid) {
        return serviceService.deleteService(uuid);
    }

}
