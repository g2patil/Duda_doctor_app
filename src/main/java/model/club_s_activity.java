package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "club_s_activity", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"name", "main_activity_id"})
	})
public class club_s_activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore /***********add for too get all sub actiity**************/
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
    @JoinColumn(name = "main_activity_id", nullable = false)
    private club_m_activity club_m_activity;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "subActivity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoleSubActivity> roleSubActivities = new HashSet<>();

    @Column(nullable = false)
    private String name;

    private String description;

   

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
    
   // @Transient
   // private List<RolePaidStatus> rolePaidStatuses;
   // @JsonIgnore
    @OneToMany(mappedBy = "club_s_activity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<RolePaidStatus> rolePaidStatuses; // No @Transient here

    // Getters and Setters
    public List<RolePaidStatus> getRolePaidStatuses() {
        return rolePaidStatuses != null ? rolePaidStatuses : new ArrayList<>();
    }

    public void setRolePaidStatuses(List<RolePaidStatus> rolePaidStatuses) {
        this.rolePaidStatuses = rolePaidStatuses;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public club_m_activity getClub_m_activity() {
		return club_m_activity;
	}

	public void setClub_m_activity(club_m_activity club_m_activity) {
		this.club_m_activity = club_m_activity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<RoleSubActivity> getRoleSubActivities() {
		return roleSubActivities;
	}

	public void setRoleSubActivities(Set<RoleSubActivity> roleSubActivities) {
		this.roleSubActivities = roleSubActivities;
	}

	

    // Getters and Setters
    
    
    
}
