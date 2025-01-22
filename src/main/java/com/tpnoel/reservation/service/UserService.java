package com.tpnoel.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpnoel.reservation.model.User;
import com.tpnoel.reservation.repository.UserRepository;

@Service
public class UserService {

   

	@Autowired
	private UserRepository  userRepository;

	
	public List<User> findAll() {
		return userRepository.findAll();
	}


	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}



	
	
	
}

	
	
	
	