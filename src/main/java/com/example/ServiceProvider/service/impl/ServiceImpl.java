package com.example.ServiceProvider.service.impl;

import com.example.ServiceProvider.dto.request.ServiceRequest;
import com.example.ServiceProvider.dto.response.*;
import com.example.ServiceProvider.model.*;
import com.example.ServiceProvider.repository.DistrictAddressRepository;
import com.example.ServiceProvider.repository.ServiceRepository;
import com.example.ServiceProvider.repository.SubCategoryRepository;
import com.example.ServiceProvider.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final SubCategoryRepository subCategoryRepository;

    private final DistrictAddressRepository districtAddressRepository;

    @Override
    public ServiceResponse createService(ServiceRequest serviceRequest) {
        Optional <SubCategoryModel> subCategoryModelOptional= subCategoryRepository.findByUuid(serviceRequest.getSubCategoryUuid());
        Optional<DistrictAddressModel>districtAddressModelOptional = districtAddressRepository.findByUuid(serviceRequest.getDistrictAddressUuid());
        SubCategoryModel subCategoryModel= null;
        DistrictAddressModel districtAddressModel = null;
        if (subCategoryModelOptional.isPresent()&&districtAddressModelOptional.isPresent()) {
            subCategoryModel = subCategoryModelOptional.get();
            districtAddressModel = districtAddressModelOptional.get();

            ServiceModel serviceModel = new ServiceModel(serviceRequest.getServiceName(),
                    serviceRequest.getPrice(),serviceRequest.getRating()
                    ,subCategoryModel,districtAddressModel);
            serviceModel.setCreatedBy("Admin");
            serviceModel.setCreatedOn(LocalDateTime.now());
            serviceModel.setUuid(UUID.randomUUID().toString());

            serviceModel = serviceRepository.save(serviceModel);

            subCategoryModel = serviceModel.getSubCategory();
            districtAddressModel = serviceModel.getDistrictAddressModel();
            CategoryModel categoryModel = subCategoryModel.getCategoryModel();
            DivisionAddressModel divisionAddressModel = districtAddressModel.getDivisionAddressModel();

            CategoryResponse categoryResponse =
                    new CategoryResponse(categoryModel.getUuid(), categoryModel.getCategoryName(),categoryModel.getCreatedBy(),
                            categoryModel.getCreatedOn(),categoryModel.getLastUpdatedBy());

            SubCategoryResponse subCategoryResponse = new SubCategoryResponse(subCategoryModel.getUuid(),
                    subCategoryModel.getSubCategoryName(),categoryResponse);
            DivisionAddressResponse divisionAddressResponse=
                    new DivisionAddressResponse(divisionAddressModel.getUuid(),divisionAddressModel.getDivisionName());
            DistrictAddressResponse districtAddressResponse =
                    new DistrictAddressResponse(districtAddressModel.getUuid(),districtAddressModel.getDistrictName(),divisionAddressResponse);


            ServiceResponse serviceResponse =
                    new ServiceResponse(serviceModel.getUuid(), serviceModel.getServiceName(), serviceModel.getPrice(), serviceModel.getRating()
                            ,subCategoryResponse,districtAddressResponse
                    );

            return serviceResponse;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Subcategory not found");




    }

    @Override
    public List<ServiceResponse> getServiceList() {
        List<ServiceModel> serviceModels = serviceRepository.findAll();

        List<ServiceResponse> serviceResponses = new ArrayList<>();

        for (ServiceModel serviceModel : serviceModels) {
            SubCategoryModel subCategoryModel = serviceModel.getSubCategory();
            CategoryModel categoryModel = new CategoryModel();
            DistrictAddressModel districtAddressModel = serviceModel.getDistrictAddressModel();
            DivisionAddressModel divisionAddressModel = new DivisionAddressModel();

            if (subCategoryModel.getCategoryModel()!=null){
                categoryModel = subCategoryModel.getCategoryModel();
            }
            if (districtAddressModel.getDivisionAddressModel()!=null){
                divisionAddressModel= districtAddressModel.getDivisionAddressModel();

            }

            CategoryResponse categoryResponse =
                    new CategoryResponse(categoryModel.getUuid(), categoryModel.getCategoryName(),categoryModel.getCreatedBy(),categoryModel.getCreatedOn(),categoryModel.getLastUpdatedBy());
            SubCategoryResponse subCategoryResponse = new SubCategoryResponse(subCategoryModel.getUuid(),
                    subCategoryModel.getSubCategoryName(),categoryResponse);
            DivisionAddressResponse divisionAddressResponse=
                    new DivisionAddressResponse(divisionAddressModel.getUuid(),divisionAddressModel.getDivisionName());
            DistrictAddressResponse districtAddressResponse =
                    new DistrictAddressResponse(districtAddressModel.getUuid(),districtAddressModel.getDistrictName(),divisionAddressResponse);

            ServiceResponse serviceResponse =
                    new ServiceResponse(serviceModel.getUuid(), serviceModel.getServiceName(), serviceModel.getPrice(),
                            serviceModel.getRating(),subCategoryResponse,districtAddressResponse);
            serviceResponses.add(serviceResponse);

        }
        return serviceResponses;
    }

    @Override
    public ServiceResponse editService(String uuid, ServiceRequest serviceRequest) {
        Optional<ServiceModel> serviceModelOptional = serviceRepository.findByUuid(uuid);

        if (serviceModelOptional.isPresent()) {

            ServiceModel serviceModel = serviceModelOptional.get();

            serviceModel.setServiceName(serviceRequest.getServiceName());

            serviceModel = serviceRepository.save(serviceModel);

            SubCategoryModel subCategoryModel = serviceModel.getSubCategory();
            CategoryModel categoryModel = subCategoryModel.getCategoryModel();

            DistrictAddressModel districtAddressModel = serviceModel.getDistrictAddressModel();
            DivisionAddressModel divisionAddressModel = districtAddressModel.getDivisionAddressModel();

            CategoryResponse categoryResponse =
                    new CategoryResponse(categoryModel.getUuid(), categoryModel.getCategoryName(),categoryModel.getCreatedBy(),categoryModel.getCreatedOn(),categoryModel.getLastUpdatedBy());
            SubCategoryResponse subCategoryResponse = new SubCategoryResponse(subCategoryModel.getUuid(),
                    subCategoryModel.getSubCategoryName(),categoryResponse);

            DivisionAddressResponse divisionAddressResponse=
                    new DivisionAddressResponse(divisionAddressModel.getUuid(),divisionAddressModel.getDivisionName());
            DistrictAddressResponse districtAddressResponse =
                    new DistrictAddressResponse(districtAddressModel.getUuid(),districtAddressModel.getDistrictName(),divisionAddressResponse);

            return new ServiceResponse(serviceModel.getUuid(), serviceModel.getServiceName(),
                    serviceModel.getPrice(), serviceModel.getRating(),subCategoryResponse,districtAddressResponse);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Service with uuid: " + uuid + " is not found.");
        }
    }

    @Override
    public boolean deleteService(String uuid) {
        Optional<ServiceModel> serviceModelOptional = serviceRepository.findByUuid(uuid);

        if (serviceModelOptional.isPresent()) {
            ServiceModel serviceModel = serviceModelOptional.get();
            serviceRepository.delete(serviceModel);

            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Service with uuid: " + uuid + " is not found.");
        }

    }



}


