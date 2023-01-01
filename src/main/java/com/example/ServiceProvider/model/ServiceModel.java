package com.example.ServiceProvider.model;

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
@Table(name = "SERVICE")
public class ServiceModel extends BaseModel {

    private String serviceName;

    private Double price;

    private Integer rating;

//    @ManyToOne
//    private CategoryModel category;

    @ManyToOne
    private SubCategoryModel subCategory;
    @ManyToOne
    private DistrictAddressModel districtAddressModel;

}
