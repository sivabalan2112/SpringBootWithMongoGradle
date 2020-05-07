package com.app.product.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.app.product.exception.ApplicationException;
import com.app.product.repository.UserRepository;
import com.app.product.repository.bean.User;

/**
 * 
 * @author Sivabalan
 *
 */
@Service
public class UserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepo;

	public String saveUser(User user) throws ApplicationException {
		String msg = "User saved successfully";
		try {
			user.setLastLoginDate(new Date());
			userRepo.addNewUser(user);
		} catch (Exception e) {
			msg = "Failed to save username already exist";
			logger.error(e.getMessage());
			throw new ApplicationException("Failed to save username already exist ");
		}
		return msg;
	}

	public String removeUser(String email) throws Exception {
		String msg = "Failed to delete user or user not found to delete";
		try {
			Long l = userRepo.deleteUserByEmail(email);
			if (l > 0) {
				msg = "User deleted successfully";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return msg;

	}

	public User getUser(User user) throws ApplicationException, Exception {
		User usr = null;
		try {
			usr = userRepo.getUserByEmailIdAndPassword(user.getEmailAddress(), user.getPassword());
			if (Objects.isNull(usr)) {
				throw new ApplicationException("Invalid email or passowrd");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return usr;

	}

	public List<User> getAllUser() throws ApplicationException {

		List<User> usr = null;
		try {
			usr = userRepo.getAllUsers();
			if (CollectionUtils.isEmpty(usr)) {
				throw new ApplicationException("No records found");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return usr;

	}

	public String updateUser(User user) throws ApplicationException {
		Long modifiedCount;
		String msg = "User not found to update";
		try {
			user.setLastLoginDate(new Date());
			modifiedCount = userRepo.updateUser(user);
			if (modifiedCount > 0) {
				msg = "User updated successfully";
				logger.info("User updated successfully");
			}
		} catch (Exception e) {
			msg = "Failed to updated user";
			logger.error(e.getMessage());
			throw new ApplicationException("Failed to updated user " + e.getMessage());
		}
		return msg;
	}
}
