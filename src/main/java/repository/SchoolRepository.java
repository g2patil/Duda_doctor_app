package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
}
