package com.reichsacht.v_chan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reichsacht.v_chan.model.User;
import com.reichsacht.v_chan.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
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
		if(repo.existsById(id)) {
			user.setId(id);
			return repo.save(user);
		}else {
			throw new RuntimeException("No se encontro el usario");
		}
	}

}
