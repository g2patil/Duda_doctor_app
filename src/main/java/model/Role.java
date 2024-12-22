package model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String role;

	 @ManyToMany(mappedBy = "roles")
	// @JsonBackReference 
	 @JsonIgnore
	    private Set<MyUser> users;
	 
	 @ManyToMany(mappedBy = "roles") // This will refer to the 'roles' field in 'ClubUser'
	// @JsonBackReference 
	 @JsonIgnore
	 private Set<ClubUser> user;
	 
	   
	   @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
	   @JsonIgnore
	   private Set<RoleSubActivity> roleSubActivities = new HashSet<>();

	public Role() {
		super();
	}

	public Role(String role) {
		super();
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<MyUser> getUsers() {
		return users;
	}

	public void setUsers(Set<MyUser> users) {
		this.users = users;
	}

	public Set<ClubUser> getUser() {
		return user;
	}

	public void setUser(Set<ClubUser> user) {
		this.user = user;
	}

	public Set<RoleSubActivity> getRoleSubActivities() {
		return roleSubActivities;
	}

	public void setRoleSubActivities(Set<RoleSubActivity> roleSubActivities) {
		this.roleSubActivities = roleSubActivities;
	}

	



}


/*
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 50, unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<MyUser> users;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MyUser> getUsers() {
        return users;
    }

    public void setUsers(Set<MyUser> users) {
        this.users = users;
    }
}
*/