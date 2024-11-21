package com.reichsacht.v_chan.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	public List<User> getAllUsers() {
		
		return repo.findAll();
	}

	public Optional<User> getUserById(Long id) {
		return repo.findById(id);
	}

	public User saveUser(User user) {
		if (user.getProfile_photo() == null) {
            user.setProfile_photo("default1.png");
        }
        return repo.save(user);
	}

	public void deleteUser(Long id) {
		repo.deleteById(id);
	}

	public User updateUser(User user, Long id) {
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
			if(user.getAccount_type()!=null) {
				existingUser.setAccount_type(user.getAccount_type());
			}
			if (user.getProfile_photo() == null) {
	            user.setProfile_photo(user.getProfile_photo());
	        }
			return repo.save(existingUser);
		}).orElseThrow(() -> new RuntimeException("No se encontró el usuario con id: " + id));
	}

	public void getUserDetails() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			username = userDetails.getUsername(); 
		} else {
			username = principal.toString(); 
		}
		Optional<User> userOptional = repo.findByUsername(username);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			String email = user.getEmail();
			String profile_photo = user.getEmail();
		}
	}

	public void updateProfilePhoto(String username, String fileName) {
		Optional<User> userOptional = repo.findByUsername(username);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setProfile_photo(fileName); 
			repo.save(user);
		} else {
			throw new RuntimeException("Usuario no encontrado");
		}
	}

	public Optional<User> findByUsername(String username) {
		return repo.findByUsername(username);
	}
}
