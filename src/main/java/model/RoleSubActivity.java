package model;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "role_sub_activity")
public class RoleSubActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "sub_activity_id", nullable = false)
    private club_s_activity subActivity;


    /*@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sub_activity_id", nullable = false)
    @JsonBackReference  
    private club_s_activity subActivity;*/

    @Column(nullable = false)
    private boolean isPaid; // true = Paid, false = Free

    @Column(name = "start_date", nullable = true)
    private LocalDate startDate; // Optional: When the sub-activity becomes available for this role

    @Column(name = "end_date", nullable = true)
    private LocalDate endDate; // Optional: When the sub-activity access ends for this role

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt = LocalDate.now(); // Automatically set when the record is created

    // Constructors
    public RoleSubActivity() {
    }

    public RoleSubActivity(Role role, club_s_activity subActivity, boolean isPaid, LocalDate startDate, LocalDate endDate) {
        this.role = role;
        this.subActivity = subActivity;
        this.isPaid = isPaid;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public club_s_activity getSubActivity() {
        return subActivity;
    }

    public void setSubActivity(club_s_activity subActivity) {
        this.subActivity = subActivity;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}

