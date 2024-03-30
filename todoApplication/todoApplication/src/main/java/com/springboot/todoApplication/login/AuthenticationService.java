package com.springboot.todoApplication.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	public boolean authenticate(String username,String password) {
		boolean isvalidUserName = username.equalsIgnoreCase("Namit");
		boolean isvalidPassword = password.equalsIgnoreCase("Namit@123");
		return isvalidUserName && isvalidPassword;
	}

}
