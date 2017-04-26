package com.mum.pm.inventory_module.dao;

import com.mum.pm.inventory_module.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by manzil on 4/25/2017.
 */
@Repository("categoryDAO")
public interface CategoryDAO extends CrudRepository<Category,Integer>{
    public Category findCategoryByCategoryName(String name);

}
