package com.mum.pm.user_module.service;

import com.mum.pm.user_module.model.Student;

/**
 * Created by 985191 on 4/27/2017.
 */

public interface StudentService {


    boolean isAccessKey(String accessKey);

    void deactivateAccessKey(String accessKey);

    Student findByStudentId(int id);
}
