package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "maharashtra_reservation")
public class MaharashtraReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resvCatId;

    private String reservationCategory;
    private String reservationCategoryMar;
    private String binduName;
    private String binduNameMar;
    private Double percentage;
    
    private LocalDate createDate;
    private String createdBy;
    private LocalDate updateDate;
    private String updatedBy;

    // Constructors
    public MaharashtraReservation() {
    }

    public MaharashtraReservation(String reservationCategory, String reservationCategoryMar, String binduName, String binduNameMar, Double percentage, LocalDate createDate, String createdBy, LocalDate updateDate, String updatedBy) {
        this.reservationCategory = reservationCategory;
        this.reservationCategoryMar = reservationCategoryMar;
        this.binduName = binduName;
        this.binduNameMar = binduNameMar;
        this.percentage = percentage;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.updateDate = updateDate;
        this.updatedBy = updatedBy;
    }

    // Getters and Setters
    public Long getResvCatId() {
        return resvCatId;
    }

    public void setResvCatId(Long resvCatId) {
        this.resvCatId = resvCatId;
    }

    public String getReservationCategory() {
        return reservationCategory;
    }

    public void setReservationCategory(String reservationCategory) {
        this.reservationCategory = reservationCategory;
    }

    public String getReservationCategoryMar() {
        return reservationCategoryMar;
    }

    public void setReservationCategoryMar(String reservationCategoryMar) {
        this.reservationCategoryMar = reservationCategoryMar;
    }

    public String getBinduName() {
        return binduName;
    }

    public void setBinduName(String binduName) {
        this.binduName = binduName;
    }

    public String getBinduNameMar() {
        return binduNameMar;
    }

    public void setBinduNameMar(String binduNameMar) {
        this.binduNameMar = binduNameMar;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "MaharashtraReservation{" +
                "resvCatId=" + resvCatId +
                ", reservationCategory='" + reservationCategory + '\'' +
                ", reservationCategoryMar='" + reservationCategoryMar + '\'' +
                ", binduName='" + binduName + '\'' +
                ", binduNameMar='" + binduNameMar + '\'' +
                ", percentage=" + percentage +
                ", createDate=" + createDate +
                ", createdBy='" + createdBy + '\'' +
                ", updateDate=" + updateDate +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
