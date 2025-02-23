package model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDate;

@Entity
@Table(name = "employee_roster",uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"educationQualification","employeeName", "dateOfBirth","reservationCategory"})
	})
public class EmployeeRoster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institute_id", nullable = false)
    private Institute institute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @Column(nullable = false, length = 100)
    private String employeeName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false, length = 50)
    private String post;

    @Column(nullable = false, length = 50)
    private String reservationCategory;

    @Column(nullable = false, length = 100)
    private String educationQualification;

    @Column(length = 200)
    private String otherQualification;

    @Column(nullable = false)
    private LocalDate dateOfJoining;

    @Column(nullable = false)
    private LocalDate dateOfRetirement;

    @Column(nullable = false)
    private LocalDate dateOfAppointment;

    private LocalDate dateOfPromotion;

    @Column(length = 50)
    private String casteCertificateNumber;

    private LocalDate casteCertificateDate;

    @Column(length = 100)
    private String casteCertificateIssuingAuthority;

    @Column(length = 50)
    private String casteValidityCertificateNumber;

    private LocalDate casteValidityCertificateDate;

    private String committeeDetailsForCasteValidityCertificate;

    private LocalDate dateOfPromotionAppointment;

    private String comments;

    @Column(nullable = false, length = 50)
    private String employeeCast;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false, length = 50)
    private String createdBy;

    @Column(nullable = false)
    private LocalDate createdAt = LocalDate.now();

    private String updatedBy;
    private LocalDate updatedAt;
    
    
    @Column(nullable = true)
    private Long bindu_Id;

    @Column(length = 50)
    private String bindu_Code;

    @Column(length = 100)
    private String bindu_Name;

    @Column(length = 50)
    private String sevarth_Id;

    @Column(length = 50)
    private String committee_Det_Caste_Validity_Cert_Number;

    @Column(length = 50)
    private LocalDate committee_Det_Caste_Validity_Cert_Date;

    @Column(length = 200)
    private String prof_Qualification;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Institute getInstitute() { return institute; }
    public void setInstitute(Institute institute) { this.institute = institute; }

    public School getSchool() { return school; }
    public void setSchool(School school) { this.school = school; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getPost() { return post; }
    public void setPost(String post) { this.post = post; }

    public String getReservationCategory() { return reservationCategory; }
    public void setReservationCategory(String reservationCategory) { this.reservationCategory = reservationCategory; }

    public String getEducationQualification() { return educationQualification; }
    public void setEducationQualification(String educationQualification) { this.educationQualification = educationQualification; }

    public String getOtherQualification() { return otherQualification; }
    public void setOtherQualification(String otherQualification) { this.otherQualification = otherQualification; }

    public LocalDate getDateOfJoining() { return dateOfJoining; }
    public void setDateOfJoining(LocalDate dateOfJoining) { this.dateOfJoining = dateOfJoining; }

    public LocalDate getDateOfRetirement() { return dateOfRetirement; }
    public void setDateOfRetirement(LocalDate dateOfRetirement) { this.dateOfRetirement = dateOfRetirement; }

    public LocalDate getDateOfAppointment() { return dateOfAppointment; }
    public void setDateOfAppointment(LocalDate dateOfAppointment) { this.dateOfAppointment = dateOfAppointment; }

    public LocalDate getDateOfPromotion() { return dateOfPromotion; }
    public void setDateOfPromotion(LocalDate dateOfPromotion) { this.dateOfPromotion = dateOfPromotion; }

    public String getCasteCertificateNumber() { return casteCertificateNumber; }
    public void setCasteCertificateNumber(String casteCertificateNumber) { this.casteCertificateNumber = casteCertificateNumber; }

    public LocalDate getCasteCertificateDate() { return casteCertificateDate; }
    public void setCasteCertificateDate(LocalDate casteCertificateDate) { this.casteCertificateDate = casteCertificateDate; }

    public String getCasteCertificateIssuingAuthority() { return casteCertificateIssuingAuthority; }
    public void setCasteCertificateIssuingAuthority(String casteCertificateIssuingAuthority) { this.casteCertificateIssuingAuthority = casteCertificateIssuingAuthority; }

    public String getCasteValidityCertificateNumber() { return casteValidityCertificateNumber; }
    public void setCasteValidityCertificateNumber(String casteValidityCertificateNumber) { this.casteValidityCertificateNumber = casteValidityCertificateNumber; }

    public LocalDate getCasteValidityCertificateDate() { return casteValidityCertificateDate; }
    public void setCasteValidityCertificateDate(LocalDate casteValidityCertificateDate) { this.casteValidityCertificateDate = casteValidityCertificateDate; }

    public String getCommitteeDetailsForCasteValidityCertificate() { return committeeDetailsForCasteValidityCertificate; }
    public void setCommitteeDetailsForCasteValidityCertificate(String committeeDetailsForCasteValidityCertificate) { this.committeeDetailsForCasteValidityCertificate = committeeDetailsForCasteValidityCertificate; }

    public LocalDate getDateOfPromotionAppointment() { return dateOfPromotionAppointment; }
    public void setDateOfPromotionAppointment(LocalDate dateOfPromotionAppointment) { this.dateOfPromotionAppointment = dateOfPromotionAppointment; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public String getEmployeeCast() { return employeeCast; }
    public void setEmployeeCast(String employeeCast) { this.employeeCast = employeeCast; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }

    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }

    public LocalDate getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDate updatedAt) { this.updatedAt = updatedAt; }
	public Long getBindu_Id() {
		return bindu_Id;
	}
	public void setBindu_Id(Long bindu_Id) {
		this.bindu_Id = bindu_Id;
	}
	public String getBindu_Code() {
		return bindu_Code;
	}
	public void setBindu_Code(String bindu_Code) {
		this.bindu_Code = bindu_Code;
	}
	public String getBindu_Name() {
		return bindu_Name;
	}
	public void setBindu_Name(String bindu_Name) {
		this.bindu_Name = bindu_Name;
	}
	public String getSevarth_Id() {
		return sevarth_Id;
	}
	public void setSevarth_Id(String sevarth_Id) {
		this.sevarth_Id = sevarth_Id;
	}
	public String getCommittee_Det_Caste_Validity_Cert_Number() {
		return committee_Det_Caste_Validity_Cert_Number;
	}
	public void setCommittee_Det_Caste_Validity_Cert_Number(String committee_Det_Caste_Validity_Cert_Number) {
		this.committee_Det_Caste_Validity_Cert_Number = committee_Det_Caste_Validity_Cert_Number;
	}
	public LocalDate getCommittee_Det_Caste_Validity_Cert_Date() {
		return committee_Det_Caste_Validity_Cert_Date;
	}
	public void setCommittee_Det_Caste_Validity_Cert_Date(LocalDate committee_Det_Caste_Validity_Cert_Date) {
		this.committee_Det_Caste_Validity_Cert_Date = committee_Det_Caste_Validity_Cert_Date;
	}
	public String getProf_Qualification() {
		return prof_Qualification;
	}
	public void setProf_Qualification(String prof_Qualification) {
		this.prof_Qualification = prof_Qualification;
	}

    

}
