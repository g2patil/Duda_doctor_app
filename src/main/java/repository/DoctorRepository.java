package repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.DoctorInfo;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorInfo, Long> {
}
