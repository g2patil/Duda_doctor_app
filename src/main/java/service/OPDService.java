package service;

import model.OPD;
import repository.OPDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
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
}