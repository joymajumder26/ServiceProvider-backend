package com.example.ServiceProvider.repository;

import com.example.ServiceProvider.model.DivisionAddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DivisionAddressRepository extends JpaRepository<DivisionAddressModel ,Long> {
    Optional<DivisionAddressModel> findByUuid(String Uuid);
}
