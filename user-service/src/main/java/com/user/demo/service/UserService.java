package com.user.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import com.user.demo.model.User;
import com.user.demo.repo.UserRepository;

public class UserService {

	@Autowired
	UserRepository userRepo;

	public List<User> getUsers() {
		List<User> usersList = new ArrayList<User>();
		userRepo.findAll().forEach(usersList::add);
		return usersList;
	}

	public Optional<User> getUserById(Integer id) {
		Optional<User> userData = userRepo.findById(id);
		return userData;
	}

	public User save(User user) {
		User userAdded = userRepo.save(user);
		return userAdded;
	}

	public User updateUserDetails(int id, User user) {
		Optional<User> userData = userRepo.findById(id);
        User userSaved = null;
		if (userData.isPresent()) {
			User userToUpdate = userData.get();
			userToUpdate.setId(user.getId());
			userToUpdate.setAddress(user.getAddress());
			userToUpdate.setName(user.getName());
			userSaved = userRepo.save(userToUpdate);
		}
		return userSaved;
	}

	public void deleteById(int id) {
		userRepo.deleteById(id);		
	}
}
