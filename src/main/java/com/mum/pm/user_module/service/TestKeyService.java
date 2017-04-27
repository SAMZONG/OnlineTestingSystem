package com.mum.pm.user_module.service;

import com.mum.pm.user_module.model.TestKey;

/**
 * Created by Chuang on 2017/4/26.
 */
public interface TestKeyService {
    String generateAndSaveTestKey(int userid, int studentId, String categoryName);
    TestKey findKey(String testKey);
}
