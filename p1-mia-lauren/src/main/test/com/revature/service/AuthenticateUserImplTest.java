package com.revature.service;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.mockito.Mock;

import com.revature.repo.UserDAO;

public class AuthenticateUserImplTest {
	
	@Mock
	private UserDAO userDao;
	
	public AuthenticateUser auth;
	
	@Before
	public void setupTest() {
		userDao = mock(UserDAO.class);
		auth = new AuthenticateUserImpl(userDao);
	}

}
