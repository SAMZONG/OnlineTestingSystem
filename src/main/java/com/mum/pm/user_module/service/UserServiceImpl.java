package com.mum.pm.user_module.service;

import com.mum.pm.user_module.model.Role;
import com.mum.pm.user_module.model.Student;
import com.mum.pm.user_module.model.TestKey;
import com.mum.pm.user_module.model.User;
import com.mum.pm.user_module.repository.RoleRepository;
import com.mum.pm.user_module.repository.StudentRepository;
import com.mum.pm.user_module.repository.TestKeyRepository;
import com.mum.pm.user_module.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestKeyRepository testKeyRepository;
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

    public Student findStudentById(int studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    @Override
    public void saveUser(User user, Role role) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Set<Role> roles = assignRole(role);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void saveStudent(Student student) {
        student.setActive(true);
        studentRepository.save(student);
    }

    @Override
    public List<Student> findAllStudent() {
        return studentRepository.findAllActiveStudents();
    }

    @Override
    public void inactiveStudent(Student student) {
        student.setActive(false);
        studentRepository.saveAndFlush(student);
    }

    @Override
    public List<Student> findAvailableStudent() {
        HashMap students = new HashMap();
        List<Student> activeStudents = studentRepository.findAllActiveStudents();
        List<TestKey> activeTestKeys = testKeyRepository.findAllActiveTestKey();
        //Remove all students who already have test key
        for (Student s : activeStudents) students.put(s.getStudentId(), s);
        for (TestKey testKey : activeTestKeys) {
            if (students.containsKey(testKey.getStudentid()))
                students.remove(testKey.getStudentid());
        }
        return new ArrayList<Student>(students.values());
    }

    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleById(int id) {
        return roleRepository.findById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAllActiveUsers();
    }

    @Override
    public User findUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public void inactiveUser(User user) {

        User user1 = findUserById(user.getId());
        user1.setActive(0);

        userRepository.saveAndFlush(user1);

    }

    private Set<Role> assignRole(Role role) {
        List<Role> listRoles = roleRepository.findAll();
        HashMap<String, Role> allRoles = new HashMap();

        for (Role temRole : listRoles) {
            allRoles.put(temRole.getRole(), temRole);
        }
        //TODO Should have an ENUM
        Set<Role> userRoles = new HashSet<>();
        if ("DBM".equalsIgnoreCase(role.getRole())) {
            userRoles.add(allRoles.get("DBM"));
        }
        if ("COACH".equalsIgnoreCase(role.getRole())) {
            userRoles.add(allRoles.get("DBM"));
            userRoles.add(allRoles.get("COACH"));
        }
        if ("ADMIN".equalsIgnoreCase(role.getRole())) {
            userRoles.add(allRoles.get("DBM"));
            userRoles.add(allRoles.get("COACH"));
            userRoles.add(allRoles.get("ADMIN"));
        }
        return userRoles;
    }
}
