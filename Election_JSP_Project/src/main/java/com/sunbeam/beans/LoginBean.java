package com.sunbeam.beans;

import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;

public class LoginBean {
	private String email;
	private String password;
	private User user;
	
	public LoginBean() {
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public void authenticate()
	{
		try(UserDaoImpl dao = new UserDaoImpl())
		{
			User u = dao.findByEmail(email);
			if(u!=null && u.getPassword().equals(this.password))
			{
				this.user =  u;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
}
