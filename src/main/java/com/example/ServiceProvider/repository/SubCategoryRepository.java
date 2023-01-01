package com.example.ServiceProvider.repository;

import com.example.ServiceProvider.model.SubCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryModel, Long> {
    Optional<SubCategoryModel>findByUuid(String Uuid);
}
