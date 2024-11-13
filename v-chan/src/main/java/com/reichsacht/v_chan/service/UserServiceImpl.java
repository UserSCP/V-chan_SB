package com.reichsacht.v_chan.service;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
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
	public User guardar(UserRegisterDTO userRegisterDTO) {
	    if (userRegisterDTO.getPassword() == null || userRegisterDTO.getPassword().isEmpty()) {
	        throw new IllegalArgumentException("La contraseña no puede ser nula o vacía.");
	    }

	    String encodedPassword = passwordEncoder.encode(userRegisterDTO.getPassword());
	    LocalDate highDate = null;
	    User user = new User(userRegisterDTO.getUsername(), userRegisterDTO.getEmail(), encodedPassword, Role.USER, LocalDate.now(), highDate);
	    return repo.save(user);
	}

}
