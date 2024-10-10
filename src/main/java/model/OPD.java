package model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "opd", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"patientId", "visitDate"})
})
public class OPD {
	
	@OneToMany(mappedBy = "opd", cascade = CascadeType.ALL)
	//@JsonIgnore
	//private List<Prescription> prescriptions = new ArrayList<>();
	@JsonManagedReference
    private List<Prescription> prescriptions;
	// Modify the setters and getters
	public List<Prescription> getPrescriptions() {
	    return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
	    this.prescriptions = prescriptions;
	}
	
	
    @Id
    @GeneratedValue
    private int opdId;

    private int patientId;
    private int doctorId;
    private int doctorid;
    public int getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(int doctorid) {
		this.doctorid = doctorid;
	}

	private LocalDate visitDate;
    private String reasonForVisit;
    private String findings;
    private String treatmentPlan;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private double feesAmount;
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.visitDate = LocalDate.now();
    }

    // Getters and Setters

    public int getOpdId() {
        return opdId;
    }

    public void setOpdId(int opdId) {
        this.opdId = opdId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
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

	public double getFeesAmount() {
		return feesAmount;
	}

	public void setFeesAmount(double feesAmount) {
		this.feesAmount = feesAmount;
	}
    
}

