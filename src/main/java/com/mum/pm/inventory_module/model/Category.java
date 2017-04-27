package com.mum.pm.inventory_module.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by manzil on 4/25/2017.
 */
@Entity
@Table(name="Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryId;
    private String categoryName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER,mappedBy = "category", orphanRemoval = true)
    private Set<SubCategory> subCategories;

    public Set<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

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

    public void addSubCategory(SubCategory subCategory){
        subCategories.add(subCategory);
        subCategory.setCategory(this);
    }

    public void removeSubCategory(SubCategory subCategory){
        subCategory.setCategory(null);
        this.subCategories.remove(subCategory);
    }

}
