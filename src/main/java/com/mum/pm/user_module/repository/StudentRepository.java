package com.mum.pm.user_module.repository;

import com.mum.pm.user_module.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Chuang on 2017/4/27.
 */
public interface StudentRepository extends JpaRepository<Student,Integer> {
    //@Query("select s from student s where u.active = 1")
    Student findByStudentId(int studentId);
}
