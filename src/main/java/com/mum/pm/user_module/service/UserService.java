package com.mum.pm.user_module.service;

import com.mum.pm.user_module.model.Role;
import com.mum.pm.user_module.model.Student;
import com.mum.pm.user_module.model.User;

import java.util.List;

public interface UserService {
	User findUserByEmail(String email);
	void saveUser(User user, Role role);
	Student findStudentById(int studentId);
	void saveStudent(Student student);
	void inactiveStudent(Student student);
	List<Student> findAvailableStudent();
	List<Student> findAllStudent();
	List<Role> findAllRole();
	Role findRoleById(int id);
}
