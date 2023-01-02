package com.example.ServiceProvider.service.impl;

import com.example.ServiceProvider.dto.request.OrderRequest;
import com.example.ServiceProvider.dto.request.ServiceRequest;
import com.example.ServiceProvider.dto.response.*;
import com.example.ServiceProvider.model.*;
import com.example.ServiceProvider.repository.DistrictAddressRepository;
import com.example.ServiceProvider.repository.OrderRepository;
import com.example.ServiceProvider.repository.ServiceRepository;
import com.example.ServiceProvider.repository.SubCategoryRepository;
import com.example.ServiceProvider.service.OrderService;
import jakarta.persistence.criteria.Order;
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
public class OrderImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ServiceRepository serviceRepository;
    private final SubCategoryRepository subCategoryRepository;

    private final DistrictAddressRepository districtAddressRepository;



    @Override
    public OrderResponse createOrder(OrderRequest orderRequest){
        Optional <SubCategoryModel> subCategoryModelOptional= subCategoryRepository.findByUuid(orderRequest.getSubCategoryUuid());
        Optional<DistrictAddressModel>districtAddressModelOptional = districtAddressRepository.findByUuid(orderRequest.getDistrictAddressUuid());
        Optional <ServiceModel> serviceModelOptional= serviceRepository.findByUuid(orderRequest.getServiceUuid());
        SubCategoryModel subCategoryModel= null;
        DistrictAddressModel districtAddressModel = null;
        ServiceModel serviceModel = null;
        if (serviceModelOptional.isPresent()&&subCategoryModelOptional.isPresent()&&districtAddressModelOptional.isPresent()) {
            subCategoryModel = subCategoryModelOptional.get();
            districtAddressModel = districtAddressModelOptional.get();
            serviceModel = serviceModelOptional.get();

            OrderModel orderModel = new OrderModel(orderRequest.getOrderName(),serviceModel,subCategoryModel,districtAddressModel);

            orderModel.setCreatedBy("Admin");
            orderModel.setCreatedOn(LocalDateTime.now());
            orderModel.setUuid(UUID.randomUUID().toString());

            orderModel = orderRepository.save(orderModel);

            serviceModel = orderModel.getServiceModel();
            subCategoryModel = orderModel.getSubCategory();
            districtAddressModel = orderModel.getDistrictAddressModel();
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


            OrderResponse orderResponse =
                    new OrderResponse(orderModel.getUuid(), orderModel.getOrderName(),serviceResponse,subCategoryResponse,districtAddressResponse);

            return orderResponse;
        }
        else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found");





}
    @Override
    public List<OrderResponse> getOrderList(){
        List<OrderModel> orderModels = orderRepository.findAll();

        List<OrderResponse> orderResponses = new ArrayList<>();

        for (OrderModel orderModel : orderModels) {
           ServiceModel serviceModel = orderModel.getServiceModel();
            SubCategoryModel subCategoryModel = orderModel.getSubCategory();
            CategoryModel categoryModel = new CategoryModel();
            DistrictAddressModel districtAddressModel = orderModel.getDistrictAddressModel();
            DivisionAddressModel divisionAddressModel = new DivisionAddressModel();

            if (subCategoryModel.getCategoryModel()!=null){
                categoryModel = subCategoryModel.getCategoryModel();
            }
            if (districtAddressModel.getDivisionAddressModel()!=null){
                divisionAddressModel= districtAddressModel.getDivisionAddressModel();

            }


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


            OrderResponse orderResponse =
                    new OrderResponse(orderModel.getUuid(), orderModel.getOrderName(),serviceResponse,subCategoryResponse,districtAddressResponse);
            orderResponses.add(orderResponse);

        }
        return orderResponses;
    }
    @Override
    public OrderResponse editOrder(String uuid, OrderRequest orderRequest){
//
//            Optional<OrderModel> orderModelOptional = orderRepository.findByUuid(uuid);
//
//            if (orderModelOptional.isPresent()) {
//                OrderModel orderModel = orderModelOptional.get();
//
//                orderModel.setOrderName(orderRequest.getOrderName());
//
//                orderModel = orderRepository.save(orderModel);
//
//                SubCategoryModel subCategoryModel = serviceModel.getSubCategory();
//                CategoryModel categoryModel = subCategoryModel.getCategoryModel();
//
//                DistrictAddressModel districtAddressModel = serviceModel.getDistrictAddressModel();
//                DivisionAddressModel divisionAddressModel = districtAddressModel.getDivisionAddressModel();
//                ServiceModel serviceModel = orderModel.getServiceModel();
//
//                CategoryResponse categoryResponse =
//                        new CategoryResponse(categoryModel.getUuid(), categoryModel.getCategoryName(),categoryModel.getCreatedBy(),categoryModel.getCreatedOn(),categoryModel.getLastUpdatedBy());
//                SubCategoryResponse subCategoryResponse = new SubCategoryResponse(subCategoryModel.getUuid(),
//                        subCategoryModel.getSubCategoryName(),categoryResponse);
//
//                DivisionAddressResponse divisionAddressResponse=
//                        new DivisionAddressResponse(divisionAddressModel.getUuid(),divisionAddressModel.getDivisionName());
//                DistrictAddressResponse districtAddressResponse =
//                        new DistrictAddressResponse(districtAddressModel.getUuid(),districtAddressModel.getDistrictName(),divisionAddressResponse);
//
//                return new OrderResponse(orderModel.getUuid(), orderModel.getOrderName());
//            } else {
//                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with uuid: " + uuid + " is not found.");
//            }
        return null;
    }

    @Override
    public  boolean deleteOrder(String uuid){
        Optional<OrderModel> orderModelOptional = orderRepository.findByUuid(uuid);

        if (orderModelOptional.isPresent()) {
            OrderModel orderModel = orderModelOptional.get();
            orderRepository.delete(orderModel);

            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with uuid: " + uuid + " is not found.");
        }
    }

}
