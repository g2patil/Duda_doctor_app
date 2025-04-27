package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.AccountMainHead;

public interface AccountMainHeadRepository extends JpaRepository<AccountMainHead, Long> {
	
	List<AccountMainHead> findByAccountType_AccountTypeId(Long accountTypeId);
}

