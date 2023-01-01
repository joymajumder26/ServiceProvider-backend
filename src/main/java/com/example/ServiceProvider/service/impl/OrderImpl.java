package com.example.ServiceProvider.service.impl;

import com.example.ServiceProvider.dto.request.OrderRequest;
import com.example.ServiceProvider.dto.request.ServiceRequest;
import com.example.ServiceProvider.dto.response.CategoryResponse;
import com.example.ServiceProvider.dto.response.OrderResponse;
import com.example.ServiceProvider.dto.response.ServiceResponse;
import com.example.ServiceProvider.model.CategoryModel;
import com.example.ServiceProvider.model.OrderModel;
import com.example.ServiceProvider.model.ServiceModel;
import com.example.ServiceProvider.repository.OrderRepository;
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

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest){
        OrderModel orderModel = new OrderModel(orderRequest.getOrderName());

        orderModel.setCreatedBy("Admin");
        orderModel.setCreatedOn(LocalDateTime.now());
        orderModel.setUuid(UUID.randomUUID().toString());

        orderModel = orderRepository.save(orderModel);

       OrderResponse orderResponse =
                new OrderResponse(orderModel.getUuid(), orderModel.getOrderName());

        return orderResponse;

    }
    @Override
    public List<OrderResponse> getOrderList(){
        List<OrderModel> orderModels = orderRepository.findAll();

        List<OrderResponse> orderResponses = new ArrayList<>();

        for (OrderModel orderModel : orderModels) {
            OrderResponse orderResponse =
                    new OrderResponse(orderModel.getUuid(), orderModel.getOrderName());
            orderResponses.add(orderResponse);

        }
        return orderResponses;
    }
    @Override
    public OrderResponse editOrder(String uuid, OrderRequest orderRequest){

            Optional<OrderModel> orderModelOptional = orderRepository.findByUuid(uuid);

            if (orderModelOptional.isPresent()) {
                OrderModel orderModel = orderModelOptional.get();

                orderModel.setOrderName(orderRequest.getOrderName());

                orderModel = orderRepository.save(orderModel);

                return new OrderResponse(orderModel.getUuid(), orderModel.getOrderName());
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with uuid: " + uuid + " is not found.");
            }
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
