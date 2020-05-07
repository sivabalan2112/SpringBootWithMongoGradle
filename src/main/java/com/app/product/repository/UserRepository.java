package com.app.product.repository;

import java.util.List;

import com.app.product.repository.bean.User;

/**
 * 
 * @author Sivabalan
 *
 */
public interface UserRepository {

	User addNewUser(User user);
	
	List<User> getAllUsers();

	User getUserByEmailIdAndPassword(String email,String password);
	
	Long updateUser(User user);
	
	Long deleteUserByEmail(String email);

}