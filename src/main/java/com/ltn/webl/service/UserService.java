package com.ltn.webl.service;

import com.ltn.webl.entity.User;

public interface UserService {

	public User findUserByEmail(String email);

	public void saveUser(User user);
}