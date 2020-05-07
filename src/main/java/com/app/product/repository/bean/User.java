package com.app.product.repository.bean;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Sivabalan
 *
 */
@Getter
@Setter
@Document(collection = "user")
@JsonIgnoreProperties(allowSetters = true, value = {"password"})
public class User {

	@Id
	private String emailAddress;
	private String name;
	private String password;
	private Date lastLoginDate;
}
