package com.example.ServiceProvider.service.impl;

import com.example.ServiceProvider.dto.request.DistrictAddressRequest;
import com.example.ServiceProvider.dto.response.CategoryResponse;
import com.example.ServiceProvider.dto.response.DistrictAddressResponse;
import com.example.ServiceProvider.dto.response.DivisionAddressResponse;
import com.example.ServiceProvider.dto.response.SubCategoryResponse;
import com.example.ServiceProvider.model.CategoryModel;
import com.example.ServiceProvider.model.DistrictAddressModel;
import com.example.ServiceProvider.model.DivisionAddressModel;
import com.example.ServiceProvider.model.SubCategoryModel;
import com.example.ServiceProvider.repository.DistrictAddressRepository;
import com.example.ServiceProvider.repository.DivisionAddressRepository;
import com.example.ServiceProvider.service.DistrictAddressService;
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
public class DistrictAddressImpl implements DistrictAddressService {
    private final DistrictAddressRepository districtAddressRepository;

    private final DivisionAddressRepository divisionAddressRepository;

    @Override
    public DistrictAddressResponse createDistrict(DistrictAddressRequest districtAddressRequest){
        Optional <DivisionAddressModel> divisionAddressModelOptional= divisionAddressRepository.findByUuid(districtAddressRequest.getDivisionUuid());
        DivisionAddressModel divisionAddressModel= null;
        if (divisionAddressModelOptional.isPresent()) {
            divisionAddressModel = divisionAddressModelOptional.get();
            DistrictAddressModel districtAddressModel = new DistrictAddressModel(districtAddressRequest.getDistrictName(),divisionAddressModel);
            districtAddressModel.setCreatedBy("Admin");
            districtAddressModel.setCreatedOn(LocalDateTime.now());
            districtAddressModel.setUuid(UUID.randomUUID().toString());
//        districtAddressModel.setLastUpdatedBy(districtAddressRequest.getLastUpdatedBy());

            districtAddressModel = districtAddressRepository.save(districtAddressModel);

            divisionAddressModel = districtAddressModel.getDivisionAddressModel();
            DivisionAddressResponse divisionAddressResponse = new DivisionAddressResponse(divisionAddressModel.getUuid()
                    ,divisionAddressModel.getDivisionName());

            DistrictAddressResponse districtAddressResponse =
                    new DistrictAddressResponse(districtAddressModel.getUuid(), districtAddressModel.getDistrictName(),divisionAddressResponse);

            return districtAddressResponse;

        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND);






    }
    @Override
    public List<DistrictAddressResponse> getDistrictList(){
        List<DistrictAddressModel> districtAddressModels = districtAddressRepository.findAll();

        List<DistrictAddressResponse> districtAddressResponses = new ArrayList<>();

        for (DistrictAddressModel districtAddressModel : districtAddressModels) {
            DivisionAddressModel divisionAddressModel = districtAddressModel.getDivisionAddressModel();
            DivisionAddressResponse divisionAddressResponse = new DivisionAddressResponse(divisionAddressModel.getUuid(),
                    divisionAddressModel.getDivisionName());
            DistrictAddressResponse districtAddressResponse =
                    new DistrictAddressResponse(districtAddressModel.getUuid(),districtAddressModel.getDistrictName(),divisionAddressResponse);
            districtAddressResponses.add(districtAddressResponse);

        }
        return districtAddressResponses;
    }


    @Override
    public DistrictAddressResponse editDistrict(String uuid, DistrictAddressRequest districtAddressRequest){
        Optional<DistrictAddressModel> districtAddressModelOptional = districtAddressRepository.findByUuid(uuid);

        if(districtAddressModelOptional.isPresent()){
            DivisionAddressResponse divisionAddressResponse = null;
            DistrictAddressModel districtAddressModel = districtAddressModelOptional.get();

            districtAddressModel.setDistrictName(districtAddressRequest.getDistrictName());

            districtAddressModel = districtAddressRepository.save(districtAddressModel);

            return new DistrictAddressResponse(districtAddressModel.getUuid(), districtAddressModel.getDistrictName(),divisionAddressResponse);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "division with uuid: " + uuid + " is not found.");
        }
    }


    @Override
    public boolean deleteDistrict(String uuid){
        Optional<DistrictAddressModel> districtAddressModelOptional = districtAddressRepository.findByUuid(uuid);

        if(districtAddressModelOptional.isPresent()){
            DistrictAddressModel districtAddressModel = districtAddressModelOptional.get();

            districtAddressRepository.delete(districtAddressModel);

            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "divisionAddressResponse with uuid: " + uuid + " is not found.");
        }

    }
}
