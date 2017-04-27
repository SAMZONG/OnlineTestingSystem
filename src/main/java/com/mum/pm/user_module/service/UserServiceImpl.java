package com.mum.pm.user_module.service;

import java.util.Arrays;
import java.util.HashSet;

import com.mum.pm.user_module.model.Role;
import com.mum.pm.user_module.model.Student;
import com.mum.pm.user_module.model.User;
import com.mum.pm.user_module.repository.RoleRepository;
import com.mum.pm.user_module.repository.StudentRepository;
import com.mum.pm.user_module.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
	@Autowired
	private StudentRepository studentRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Student findStudentById(int studentId){ return studentRepository.findByStudentId(studentId);}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public void saveStudent(Student student) {
		studentRepository.save(student);
	}
}
