package com.revature.presentation;

import java.util.Scanner;

import com.revature.service.AuthenticateUser;
import com.revature.service.AuthenticateUserImpl;

public class UserLogin {

	AuthenticateUser auth; // = new AuthenticateUserImpl();
	
	public UserLogin(AuthenticateUser auth) {
		this.auth = auth;
	}
	
	public static void userLoginMenu() {
		
//		Scanner s = new Scanner(System.in);
		

	}
	
	public boolean display() {
		
		boolean success = false;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome! Please enter your credentials: ");
		System.out.println("");
		System.out.println("Username: ");
		String username = sc.nextLine();
		System.out.println("Password");
		String password = sc.nextLine();
		
//		if(username != null && password != null) {
//			success = true;
//		}
		
//		System.out.println( auth.authenticate(username, password));
		
		return auth.authenticate(username, password);
	}
}
