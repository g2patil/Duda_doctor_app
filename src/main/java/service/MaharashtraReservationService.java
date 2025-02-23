package service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.MaharashtraReservation;
import repository.MaharashtraReservationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MaharashtraReservationService {

    @Autowired
    private MaharashtraReservationRepository repository;

    // Add Category
    public MaharashtraReservation addCategory(MaharashtraReservation reservation) {
        reservation.setCreateDate(LocalDate.now());
        reservation.setCreatedBy("Admin");
        return repository.save(reservation);
    }

    // Get All Categories
    public List<MaharashtraReservation> getAllCategories() {
        return repository.findAll();
    }

    // Get Category by ID
    public Optional<MaharashtraReservation> getCategoryById(Long resvCatId) {
        return repository.findById(resvCatId);
    }

    // Update Category
    public MaharashtraReservation updateCategory(Long resvCatId, MaharashtraReservation updatedReservation) {
        Optional<MaharashtraReservation> existingReservation = repository.findById(resvCatId);

        if (existingReservation.isPresent()) {
            MaharashtraReservation reservation = existingReservation.get();
            reservation.setReservationCategory(updatedReservation.getReservationCategory());
            reservation.setReservationCategoryMar(updatedReservation.getReservationCategoryMar());
            reservation.setBinduName(updatedReservation.getBinduName());
            reservation.setBinduNameMar(updatedReservation.getBinduNameMar());
            reservation.setPercentage(updatedReservation.getPercentage());
            reservation.setUpdateDate(LocalDate.now());
            reservation.setUpdatedBy("Admin");
            return repository.save(reservation);
        } else {
            throw new RuntimeException("Category not found with ID: " + resvCatId);
        }
    }

 // Add multiple categories
    public List<MaharashtraReservation> addCategories(List<MaharashtraReservation> reservations) {
        for (MaharashtraReservation reservation : reservations) {
            reservation.setCreateDate(LocalDate.now());
            reservation.setCreatedBy("Admin");
        }
        return repository.saveAll(reservations);
    }
    
    // Get Bindu Name by Reservation Category ID
    public List<MaharashtraReservation> getBinduNameByResvCatId(Long resvCatId) {
        return repository.findByResvCatId(resvCatId);
    }
}
