package com.revature.service;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.mockito.Mock;

import com.revature.models.User;
import com.revature.repo.UserDAOImpl;

public class AuthenticateUserImplTest {
	
	@Mock 
	private UserDAOImpl userDao;
	
	public AuthenticateUserImpl userAuth;
	
	@Before
	public void setupTest() {
		userDao = mock(UserDAOImpl.class);
		userAuth = new AuthenticateUserImpl(userDao);
		
//		when(userDao.selectUserByUsername("employee").thenReturn(new User("employee", "employee", "employee")));
		
	}
	

}
