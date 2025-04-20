package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.SchoolTransaction;

public interface SchoolTransactionRepository extends JpaRepository<SchoolTransaction, Long> {
}
