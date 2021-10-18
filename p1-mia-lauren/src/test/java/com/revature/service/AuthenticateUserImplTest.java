package com.revature.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.mockito.Mock;

import com.revature.models.User;
import com.revature.repo.UserDAOImpl;

public class AuthenticateUserImplTest {
	
	@Mock 
	private UserDAOImpl userDao;
	private User employee = new User("employee", "employee", "employee");
	private User manager = new User("finance", "manager", "finance manager");
	
	public AuthenticateUserImpl userAuth;
	
	@Before
	public void setupTest() {
		userDao = mock(UserDAOImpl.class);
		userAuth = new AuthenticateUserImpl(userDao);
		
		doReturn(employee).when(userDao.selectUserByUsername("employee"));
		doReturn(manager).when(userDao.selectUserByUsername("finance"));		
		
	}
	
	public void testGetUser() {
		assertEquals(employee, userAuth.getUser("employee"));
		assertEquals(manager, userAuth.getUser("finance"));
	}
	
	public void testAuthenticate() {
		assertEquals(employee, userAuth.authenticate("employee", "employee"));
		assertEquals(manager, userAuth.authenticate("finance", "manager"));
	}

}
