package com.mum.pm.user_module.repository;

import com.mum.pm.user_module.model.TestKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Chuang on 2017/4/26.
 */
public interface TestKeyRepository extends JpaRepository<TestKey, Integer>{
    TestKey findByTestkeyValue(String testKeyValue);
}
