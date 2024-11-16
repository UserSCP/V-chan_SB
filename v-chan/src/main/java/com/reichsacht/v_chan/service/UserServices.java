package com.reichsacht.v_chan.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import com.reichsacht.v_chan.model.Role;
import com.reichsacht.v_chan.model.User;
import com.reichsacht.v_chan.repository.UserRepository;

@Service
public class UserServices {
	@Autowired
	private UserRepository repo;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	public List<User> getAllUsers(){
		return repo.findAll();
	}
	public Optional<User> getUserById(Long id){
		return repo.findById(id);
	}
	public User saveUser(User user) {
		return repo.save(user);
	}
	public void deleteUser(Long id) {
		repo.deleteById(id);
	}
	public User updateUser(Long id, User user) {
	    return repo.findById(id).map(existingUser -> {
	        if (user.getUsername() != null) {
	            existingUser.setUsername(user.getUsername());
	        }
	        if (user.getEmail() != null) {
	            existingUser.setEmail(user.getEmail());
	        }
	        if (user.getRole() != null) {
	            existingUser.setRole(user.getRole());
	        }
	        return repo.save(existingUser);
	    }).orElseThrow(() -> new RuntimeException("No se encontró el usuario con id: " + id));
	}
}
