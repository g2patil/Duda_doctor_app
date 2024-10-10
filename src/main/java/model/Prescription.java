package model;


import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue
    private int prescriptionId;

    @ManyToOne
    @JoinColumn(name = "opdId", nullable = false)
    @JsonBackReference
    private OPD opd;
    @Column(name = "medicine_name") // Explicit column mapping
    private String medicineName;
    private String dosage;
    @Column(name = "no_of_days") // Explicit column mapping
    private int noOfDays;
    private String instructions;
    
       
    
	public int getPrescriptionId() {
		return prescriptionId;
	}
	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}
	public OPD getOpd() {
		return opd;
	}
	public void setOpd(OPD opd) {
		this.opd = opd;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public int getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
    
	

    // Getters and Setters
   
}
