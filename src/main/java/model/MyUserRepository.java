package model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser , Long> {
	
 //Optional<MyUser> findByUsername(String username);
 
 Optional<MyUser> findByUsername(String username);

//void save(Role role);



}
