package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RolePaidStatus {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generate ID for this entity
	    private Long id;  // Primary key for the RolePaidStatus entity

    @ManyToOne
    @JoinColumn(name = "club_s_activity_id") // Foreign key column
    private club_s_activity club_s_activity;  // The owning side of the relationship

    private String role;
    private Long roleId;
    private boolean paid;

    
    public club_s_activity getClub_s_activity1() {
        return club_s_activity;
    }

    public void setClub_s_activity1(club_s_activity club_s_activity) {
        this.club_s_activity = club_s_activity;
    }
    
    // Getters and setters...
    
    

    public club_s_activity getClub_s_activity() {
        return club_s_activity;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setClub_s_activity(club_s_activity club_s_activity) {
        this.club_s_activity = club_s_activity;
    }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

    // Other fields and methods...
    
}
