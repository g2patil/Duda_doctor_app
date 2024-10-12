package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor_info")
public class DoctorInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctor_id; // Doctor ID

    @Column(name = "doctor_name", length = 255)
    private String doctor_name; // Doctor Name
    @Column(name = "doctor_dg", length = 100)
    private String doctor_dg; // Doctor Degree
    @Column(name = "doctor_reg_no", length = 100)
    private String doctor_reg_no; // Doctor Registration Number
    @Column(name = "doctor_oth_info", length = 255)
    private String doctor_oth_info; // Other Information

    // Getters and Setters

    public Integer getDoctor_d() {
        return doctor_id;
    }

    public void setDoctor_d(Integer doctor_d) {
        this.doctor_id = doctor_d;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_dg() {
        return doctor_dg;
    }

    public void setDoctor_dg(String doctor_dg) {
        this.doctor_dg = doctor_dg;
    }

    public String getDoctor_reg_no() {
        return doctor_reg_no;
    }

    public void setDoctor_reg_no(String doctor_reg_no) {
        this.doctor_reg_no = doctor_reg_no;
    }

    public String getDoctor_oth_info() {
        return doctor_oth_info;
    }

    public void setDoctor_oth_info(String doctor_oth_info) {
        this.doctor_oth_info = doctor_oth_info;
    }
}
