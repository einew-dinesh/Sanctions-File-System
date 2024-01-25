package com.effiya.Maker_Checker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.effiya.Maker_Checker.dto.LoginDTO;
import com.effiya.Maker_Checker.dto.SignupDTO;
import com.effiya.Maker_Checker.model.User;
import com.effiya.Maker_Checker.payload.LoginMessage;

@Service
public interface UserService {
	
	String addUser(SignupDTO signupDTO);
	LoginMessage loginUser(LoginDTO loginDTO);
	User getUser(Integer userId);
	User getUserByEmail(String userEmail);
	int countOfUserByRole(String userRole); 
	List<User> getUsersByUserRole(String userRole);
	

}
