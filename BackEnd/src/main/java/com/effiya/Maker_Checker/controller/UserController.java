package com.effiya.Maker_Checker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effiya.Maker_Checker.dto.LoginDTO;
import com.effiya.Maker_Checker.dto.SignupDTO;
import com.effiya.Maker_Checker.model.User;
import com.effiya.Maker_Checker.payload.LoginMessage;
import com.effiya.Maker_Checker.payload.ResponseMessage;
import com.effiya.Maker_Checker.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<ResponseMessage> addUser(@RequestBody SignupDTO signupDTO) {
		String message = userService.addUser(signupDTO);
		ResponseMessage responseMessage = new ResponseMessage(message);
		return ResponseEntity.ok(responseMessage);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
		System.out.println(loginDTO);
		LoginMessage loginMessage = userService.loginUser(loginDTO);
		return ResponseEntity.ok(loginMessage);
	}

	@GetMapping("userId/{user_id}")
	public ResponseEntity<User> getUser(@PathVariable(name = "user_id") int userId) {
		try {
			User user = userService.getUser(userId);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("userEmail/{user_email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable(name = "user_email") String userEmail) {
		try {
			User user = userService.getUserByEmail(userEmail);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}