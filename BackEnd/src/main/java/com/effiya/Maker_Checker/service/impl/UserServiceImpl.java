package com.effiya.Maker_Checker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effiya.Maker_Checker.dto.LoginDTO;
import com.effiya.Maker_Checker.dto.SignupDTO;
import com.effiya.Maker_Checker.model.User;
import com.effiya.Maker_Checker.payload.LoginMessage;
import com.effiya.Maker_Checker.repository.UserRepo;
import com.effiya.Maker_Checker.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Override
	public String addUser(SignupDTO signupDTO) {
		// TODO Auto-generated method stub

		User user = new User(signupDTO.getUserName(), signupDTO.getUserEmail(), signupDTO.getUserRole(),signupDTO.getPassword());
		userRepo.save(user);
		return user.getUserName();
	}

	@Override
	public LoginMessage loginUser(LoginDTO loginDTO) {
		// TODO Auto-generated method stub

		User user = userRepo.findByUserEmail(loginDTO.getUserEmail());
		if (user != null) {
			Boolean isPwdRight = loginDTO.getPassword().equals(user.getPassword());
			if (isPwdRight) {
				return new LoginMessage("Login Success", true,user.getUserId(),user.getUserRole());
			} else {
				return new LoginMessage("password Not Match", false);
			}
		} else {
			return new LoginMessage("Email not exits", false);
		}

	}

	@Override
	public User getUser(Integer userId) {
		// TODO Auto-generated method stub

		System.out.println("ghjkhgfdsdfghjhgfdfj");
		return userRepo.findById(userId).orElseThrow();

	}

	@Override
	public User getUserByEmail(String userEmail) {
		// TODO Auto-generated method stub
		User user = userRepo.findByUserEmail(userEmail);
		return user;
	}
	

	@Override
	public int countOfUserByRole(String userRole) {
		// TODO Auto-generated method stub
		int userCount = userRepo.countByUserRole(userRole);
		return userCount;
	}

	@Override
	public List<User> getUsersByUserRole(String userRole) {
		// TODO Auto-generated method stub
		List<User> users = userRepo.findAllByUserRole(userRole);
		return users;
	}

	

}
