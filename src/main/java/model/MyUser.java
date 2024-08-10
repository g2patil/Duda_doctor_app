package model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
    @Column(length =70 , unique = true, nullable = false)
    private String username;
    @Column(length =255)
    private String password;
    @Column(length =30)
    private String role;
    @Column(length =10)
    private long mob;
	public long getId() {
		return id;
	}
	public long getMob() {
		return mob;
	}
	public void setMob(long mob) {
		this.mob = mob;
	}
	public void setId(long id) {
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public List<MyUser> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
