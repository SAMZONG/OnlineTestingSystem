package com.mum.pm.user_module.service;

import com.mum.pm.user_module.model.Role;
import com.mum.pm.user_module.model.Student;
import com.mum.pm.user_module.model.User;
import com.mum.pm.user_module.repository.RoleRepository;
import com.mum.pm.user_module.repository.StudentRepository;
import com.mum.pm.user_module.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

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
	public void saveUser(User user, Role role) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
		Set<Role> roles = assignRole(role);
		user.setRoles(roles);
		userRepository.save(user);
	}

	@Override
	public void saveStudent(Student student) {
		studentRepository.save(student);
	}

	@Override
	public List<Student> findAllStudent() {
		return studentRepository.findAll();
	}

	@Override
	public List<Role> findAllRole() {
		return roleRepository.findAll();
	}

	@Override
	public Role findRoleById(int id) {
		return roleRepository.findById(id);
	}

	private Set<Role> assignRole(Role role){
		List<Role> listRoles = roleRepository.findAll();
		HashMap<String, Role> allRoles = new HashMap();

		for(Role temRole : listRoles){
			allRoles.put(temRole.getRole(),temRole);
		}
		//TODO Should have an ENUM
		Set<Role> userRoles = new HashSet<>();
		if("DBM".equalsIgnoreCase(role.getRole())){
			userRoles.add(allRoles.get("DBM"));
		}
		if("COACH".equalsIgnoreCase(role.getRole())){
			userRoles.add(allRoles.get("DBM"));
			userRoles.add(allRoles.get("COACH"));
		}
		if("ADMIN".equalsIgnoreCase(role.getRole())) {
			userRoles.add(allRoles.get("DBM"));
			userRoles.add(allRoles.get("COACH"));
			userRoles.add(allRoles.get("ADMIN"));
		}
		return  userRoles;
	}
}
