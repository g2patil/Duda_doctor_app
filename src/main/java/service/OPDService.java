package service;

import model.OPD;
import model.Prescription;
import repository.OPDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OPDService {

    @Autowired
    private OPDRepository opdRepository;

    public OPD createOPD(OPD opd) {
        opd.setCreatedAt(LocalDateTime.now());
        opd.setUpdatedAt(LocalDateTime.now());
        return opdRepository.save(opd);
    }

    public OPD getOPDById(UUID opdId) {
        return opdRepository.findById(opdId).orElse(null);
    }

    public OPD updateOPD(UUID opdId, OPD opdDetails) {
        OPD opd = opdRepository.findById(opdId).orElse(null);
        if (opd != null) {
            opd.setPatientId(opdDetails.getPatientId());
            opd.setDoctorId(opdDetails.getDoctorId());
            opd.setVisitDate(opdDetails.getVisitDate());
            opd.setReasonForVisit(opdDetails.getReasonForVisit());
            opd.setFindings(opdDetails.getFindings());
            opd.setTreatmentPlan(opdDetails.getTreatmentPlan());
            opd.setUpdatedAt(LocalDateTime.now());
            return opdRepository.save(opd);
        }
        return null;
    }

    public void deleteOPD(UUID opdId) {
        opdRepository.deleteById(opdId);
    }
    
    
    public List<OPD> getOPDRecords(int patientId, int doctorId) {
        // Using the derived query method
        return opdRepository.findByPatientIdAndDoctorId(patientId, doctorId);
        
        // Alternatively, using the custom query method
        // return opdRepository.findByPatientIdAndDoctorIdCustom(patientId, doctorId);
    }
    
    public OPD createOpdWithPrescriptions(OPD opd) {
        for (Prescription prescription : opd.getPrescriptions()) {
            prescription.setOpd(opd);  // Set the OPD reference in the prescription
        }
        return opdRepository.save(opd);
    }
    
    
}
