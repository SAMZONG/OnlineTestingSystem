package com.mum.pm.user_module.repository;

import com.mum.pm.user_module.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Chuang on 2017/4/27.
 */
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByStudentId(int studentId);
}
