package com.mum.pm.inventory_module.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by manzil on 4/25/2017.
 */
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryId;
    private String categoryName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER,mappedBy = "category", orphanRemoval = true)
    private Set<SubCategory> subCategories;

    public Category() {
        subCategories=new HashSet<>();
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
