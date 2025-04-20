package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.AccountType;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {}

