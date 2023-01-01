package com.example.ServiceProvider.repository;

import com.example.ServiceProvider.model.DistrictAddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistrictAddressRepository extends JpaRepository<DistrictAddressModel ,Long> {

    Optional<DistrictAddressModel> findByUuid(String Uuid);
}
