package com.reichsacht.v_chan.service;

import com.reichsacht.v_chan.dto.UserRegisterDTO;
import com.reichsacht.v_chan.model.User;

public interface UserService {
	public User guardar(UserRegisterDTO userRegisteDTO);

}
