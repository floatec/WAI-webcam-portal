package model;

import dao.UserDao;
import dao.UserDaoImpl;

public class User {
	private Long id;
	private String username;
	private String password;
	private String saltValue;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
		
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSaltValue() {
		return saltValue;
	}
	
	public void setSaltValue(String saltValue) {
		this.saltValue = saltValue;
	}
	
	public boolean isPasswordEqual(String pwd){
		String password = pwd;
		for (int i = 0; i < UserDaoImpl.ITERATION; i++) {
			password = UserDaoImpl.sha256(password+saltValue);
		}
		System.out.println(pwd);
		System.out.println(password);
		System.out.println(this.password);
		return this.password.equals(password);
	}
}
