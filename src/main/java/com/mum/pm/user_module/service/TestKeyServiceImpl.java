package com.mum.pm.user_module.service;

import com.mum.pm.user_module.model.TestKey;
import com.mum.pm.user_module.repository.TestKeyRepository;
import com.mum.pm.util.CalendarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Chuang on 2017/4/26.
 */
@Service("testKeyService")
public class TestKeyServiceImpl implements TestKeyService {
    private static final Logger logger = LoggerFactory.getLogger(TestKeyServiceImpl.class);

    @Autowired
    private TestKeyRepository testKeyRepository;
    @Autowired
    private CalendarUtil calendarUtil;

    @Override
    public String generateAndSaveTestKey(int userid, int studentId) {
        String testKeyValue = UUID.randomUUID().toString().replaceAll("-", "");
        logger.debug("TestKey created as : " + testKeyValue);
        try {
            testKeyRepository.save(createTestKey(testKeyValue, userid, studentId));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw (ex);
        }
        return testKeyValue;
    }

    @Override
    public TestKey findKey(String testKeyValue) {
        logger.debug("Trying to find testKeyValue : " + testKeyValue);
        return testKeyRepository.findByTestkeyValue(testKeyValue);
    }


    private TestKey createTestKey(String testKeyValue, int userid, int studentId) {

        TestKey testKey = new TestKey();
        testKey.setTestkeyValue(testKeyValue);
        testKey.setStudentid(studentId);
        testKey.setUserid(userid);
        testKey.setCreatedatetime(calendarUtil.getDate(1));
        testKey.setExpiredatetime(calendarUtil.getDate(4));
        testKey.setActive(1);
        return testKey;
    }


}
