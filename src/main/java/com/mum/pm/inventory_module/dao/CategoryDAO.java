package com.mum.pm.inventory_module.dao;

import com.mum.pm.inventory_module.model.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by manzil on 4/25/2017.
 */
@Repository("categoryDAO")
public interface CategoryDAO extends CrudRepository<Category,Integer>{
    public Category findCategoryByCategoryName(String name);

    @Modifying
    @Query( "select c from Category c where c.active in :number" )
    List<Category> findByCategoryActive(@Param("number") int number);
}
