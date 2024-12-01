package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.RoleSubActivity;

@Repository
public interface RoleSubActivityRepository extends JpaRepository<RoleSubActivity, Long> {
  }

