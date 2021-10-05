package com.revature.repo;

import com.revature.models.User;

public interface UserDAO {

	public User selectUserByUsername(String username);
}
