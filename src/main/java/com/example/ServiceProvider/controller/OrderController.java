package com.example.ServiceProvider.controller;

import com.example.ServiceProvider.dto.request.CategoryRequest;
import com.example.ServiceProvider.dto.request.OrderRequest;
import com.example.ServiceProvider.dto.request.ServiceRequest;
import com.example.ServiceProvider.dto.response.CategoryResponse;
import com.example.ServiceProvider.dto.response.OrderResponse;
import com.example.ServiceProvider.dto.response.ServiceResponse;
import com.example.ServiceProvider.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }
    @GetMapping
    public List<OrderResponse> getOrderList(){return orderService.getOrderList();
    }

    @PutMapping("/{order-uuid}")
    public OrderResponse editOrder(@PathVariable("order-uuid") String uuid,
                                         @RequestBody OrderRequest orderRequest){
        return orderService.editOrder(uuid, orderRequest);
    }

    @DeleteMapping("/{order-uuid}")
    public boolean deleteOrder(@PathVariable("order-uuid") String uuid) {
        return orderService.deleteOrder(uuid);
    }
}
