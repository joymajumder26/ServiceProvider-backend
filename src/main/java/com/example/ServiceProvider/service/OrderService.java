package com.example.ServiceProvider.service;

import com.example.ServiceProvider.dto.request.OrderRequest;
import com.example.ServiceProvider.dto.response.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);

    List<OrderResponse> getOrderList();

    OrderResponse editOrder(String uuid, OrderRequest orderRequest);

    boolean deleteOrder(String uuid);
}
