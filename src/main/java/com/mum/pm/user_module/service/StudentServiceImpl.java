package com.mum.pm.user_module.service;

import com.mum.pm.user_module.model.TestKey;
import com.mum.pm.user_module.repository.TestKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 985191 on 4/27/2017.
 */

@Service("studentService")
public class StudentServiceImpl implements StudentService{


    @Autowired
    private TestKeyRepository testKeyRepository;
    @Override
    public boolean isAccessKey(String accessKey) {
        TestKey testKey = testKeyRepository.findByTestkeyValue(accessKey);
       if( testKey!=null && testKey.getTestkeyValue().equals(accessKey) ){
           if(testKey.getActive() == 1){
               return true;
           }
       }
       return false;

    }

    @Override
    public void deactivateAccessKey(String accessKey) {
        TestKey testKey = testKeyRepository.findByTestkeyValue(accessKey);
        testKey.setActive(0);
        testKeyRepository.saveAndFlush(testKey);
    }
}
