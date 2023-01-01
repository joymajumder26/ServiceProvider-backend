package com.example.ServiceProvider.repository;

import com.example.ServiceProvider.model.CategoryModel;
import com.example.ServiceProvider.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {

    Optional<OrderModel> findByUuid(String uuid);
}
