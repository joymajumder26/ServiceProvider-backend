package com.example.ServiceProvider.repository;

import com.example.ServiceProvider.model.CategoryModel;
import com.example.ServiceProvider.model.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceModel, Long> {

    Optional<ServiceModel> findByUuid(String uuid);
}
