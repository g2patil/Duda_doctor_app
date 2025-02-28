package repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.MaharashtraReservation;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaharashtraReservationRepository extends JpaRepository<MaharashtraReservation, Long> {
    List<MaharashtraReservation> findByResvCatId(Long resvCatId);
  //  Optional<MaharashtraReservation> findByResvCatId(Long binduCode);
}
