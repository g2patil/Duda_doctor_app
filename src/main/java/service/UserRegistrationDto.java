package service;

import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class UserRegistrationDto {

    private String username;
    private String password;
    private String email;
    private long mob;
    
    @JsonDeserialize(using = RoleSetDeserializer.class)
    private String roles; // You can accept roles as strings and then convert them to `Role` entities
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public long getMob() {
		return mob;
	}
	public void setMob(long mob) {
		this.mob = mob;
	}
	

    // Getters and setters
}