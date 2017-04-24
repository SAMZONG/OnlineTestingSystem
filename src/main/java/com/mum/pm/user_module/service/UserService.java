package com.mum.pm.user_module.service;

import com.mum.pm.user_module.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
