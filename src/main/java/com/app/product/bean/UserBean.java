package com.app.product.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBean {

	private String emailAddress;
	private String name;
	private String password;
	private Date lastLoginDate;

}
