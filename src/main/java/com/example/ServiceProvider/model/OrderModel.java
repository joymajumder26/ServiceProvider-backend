package com.example.ServiceProvider.model;

import com.example.ServiceProvider.service.OrderService;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"ORDER\"")
public class OrderModel extends BaseModel {

    private String orderName;

    @ManyToOne
    private ServiceModel serviceModel;
    @ManyToOne
    private SubCategoryModel subCategory;
    @ManyToOne
    private DistrictAddressModel districtAddressModel;

}
