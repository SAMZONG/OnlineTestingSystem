package com.mum.pm.user_module.service;

import com.mum.pm.user_module.model.Student;
import com.mum.pm.user_module.model.User;

public interface UserService {
	 User findUserByEmail(String email);
	 void saveUser(User user);
	 Student findStudentById(int studentId);
	 void saveStudent(Student student);

}
