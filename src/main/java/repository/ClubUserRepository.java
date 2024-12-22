package repository;


import model.ClubUser;
import model.MyUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface ClubUserRepository extends JpaRepository<ClubUser, Long> {
	@Transactional
	Optional<ClubUser> findByUsername(String username);
	
	
	
	
	//Optional<MyUser> findByUsername(String username);
	Optional<ClubUser> findByMob(String mobile);


}
