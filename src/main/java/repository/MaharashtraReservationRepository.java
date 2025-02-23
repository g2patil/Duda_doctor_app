package repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.MaharashtraReservation;

import java.util.List;

@Repository
public interface MaharashtraReservationRepository extends JpaRepository<MaharashtraReservation, Long> {
    List<MaharashtraReservation> findByResvCatId(Long resvCatId);
}
