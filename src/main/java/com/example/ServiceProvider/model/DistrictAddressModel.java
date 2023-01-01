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
@Table(name = "DISTRICTADDRESS")
public class DistrictAddressModel extends BaseModel {
    private String districtName;

    @ManyToOne
    private DivisionAddressModel divisionAddressModel;
}
