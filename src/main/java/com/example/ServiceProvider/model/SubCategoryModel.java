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
@Table(name = "SUB_CATEGORY")
public class SubCategoryModel extends BaseModel {

    private String subCategoryName;

    @ManyToOne
    private CategoryModel categoryModel;

}
