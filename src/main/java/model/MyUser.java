package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class MyUser {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
    @Column(length =70 , unique = true, nullable = false)
    private String username;
    @Column(length =255)
    private String password;
 //   @Column(length =30)
  //  private Role role;
    @Column(length =10)
    private long mob;
    
    private String Role_name;
    
    public String getRole_name() {
		return Role_name;
	}
	public void setRole_name(String role_name) {
		Role_name = role_name;
	}



	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
	
    private Set<Role> roles = new HashSet<>();
    
    
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

	
	public List<MyUser> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	
	

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	

	
	
}
