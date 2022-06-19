package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.User;

public interface UserService {
	User createUser(User user);

	User findById(Long id);
	
	void update(User user);
	
	void delete(Long id);
	
	List<User> getAll();
}
