package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "institute",uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"name", "address"})
	})
public class Institute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 500)
    private String address;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, length = 50)
    private String createdBy;

    @Column(length = 50)
    private String updatedBy;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "institute", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<School> schools;

    @OneToMany(mappedBy = "institute", fetch = FetchType.LAZY)
   // @JsonManagedReference
    @JsonIgnore
    private List<EmployeeRoster> employeeRosters;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<School> getSchools() {
		return schools;
	}

	public void setSchools(List<School> schools) {
		this.schools = schools;
	}

	public List<EmployeeRoster> getEmployeeRosters() {
		return employeeRosters;
	}

	public void setEmployeeRosters(List<EmployeeRoster> employeeRosters) {
		this.employeeRosters = employeeRosters;
	}

    // Getters and Setters
	// Constructors
    public Institute() {}

    public Institute(String name, String address, String createdBy, LocalDateTime createdAt) {
        this.name = name;
        this.address = address;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

}
