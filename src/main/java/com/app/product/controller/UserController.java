package com.app.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.product.exception.ApplicationException;
import com.app.product.repository.bean.User;
import com.app.product.service.UserService;

/**
 * 
 * @author Sivabalan
 *
 */
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/saveUser")
	public ResponseEntity<String> saveUser(@RequestBody User user) throws ApplicationException {
		String msg = userService.saveUser(user);
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@PutMapping("/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody User user) throws ApplicationException {
		String msg = userService.updateUser(user);
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@PostMapping("/login")
	public ResponseEntity<User> getUser(@RequestBody User user) throws ApplicationException, Exception {
		User usr = userService.getUser(user);
		return new ResponseEntity<User>(usr, HttpStatus.OK);

	}

	@DeleteMapping("/deleteUser/{email}")
	public ResponseEntity<String> deleteUser(@PathVariable String email) throws Exception {
		String msg = userService.removeUser(email);
		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser() throws ApplicationException {
		List<User> usr = userService.getAllUser();
		return new ResponseEntity<List<User>>(usr, HttpStatus.OK);

	}
}
