package com.reichsacht.v_chan.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reichsacht.v_chan.dto.UserRegisterDTO;
import com.reichsacht.v_chan.model.Role;
import com.reichsacht.v_chan.model.User;
import com.reichsacht.v_chan.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository repo;
	private PasswordEncoder passwordEncoder;


	public UserServiceImpl(UserRepository repo, PasswordEncoder passwordEncoder) {
		super();
		this.repo = repo;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public User guardar(UserRegisterDTO userRegisteDTO) {
		String encodedPassword = passwordEncoder.encode(userRegisteDTO.getPassword());
		User user = new User(userRegisteDTO.getUsername(), userRegisteDTO.getEmail(), encodedPassword, Role.USER);
		return repo.save(user);
	}
	
	
	

}
