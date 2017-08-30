package com.rbs.scm.teama_login.model.beans;


public class GenericUser {
	private String username;
	private String password;
	private boolean isActive;
	private boolean isConfirmed;
	private boolean is_Bank_User;
	
	
	public GenericUser(String username, String password,boolean isConfirmed, boolean isActive,boolean is_Bank_User) {
		this.username = username;
		this.password = password;
		this.isActive = isActive;
		this.isConfirmed = isConfirmed;
		this.is_Bank_User=is_Bank_User;
	}
	
	
	
	@Override
	public String toString() {
		return "GenericUser [username=" + username +  "]";
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
	
	public boolean get_is_Bank_User() {
		return is_Bank_User;
	}
	public void set_is_Bank_User(boolean is_Bank_User) {
		this.is_Bank_User = is_Bank_User;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isConfirmed() {
		return isConfirmed;
	}
	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}
	
	
	
	
	
	
	
}
