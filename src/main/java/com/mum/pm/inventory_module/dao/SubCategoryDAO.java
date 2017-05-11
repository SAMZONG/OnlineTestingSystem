package com.mum.pm.inventory_module.dao;


import com.mum.pm.inventory_module.model.Category;
import com.mum.pm.inventory_module.model.SubCategory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by manzil on 4/27/2017.
 */

@Repository("subCategoryDAO")
public interface SubCategoryDAO extends CrudRepository<SubCategory, Integer> {
    public Iterable<SubCategory> findByCategory(Category category);

    public Set<SubCategory> getSubCategoriesByCategory_CategoryId(int id);

    public SubCategory findBySubCategoryName(String name);

    public SubCategory findSubCategoryBySubCategoryName(String name);

    @Modifying
    @Query("select sc from SubCategory sc where sc.active = :number and sc.category.categoryId = :id")
    List<SubCategory> findBySubCategoryActive(@Param("number") int number, @Param("id") int id);
}
