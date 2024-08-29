package repository;

import model.ERole;
import model.MyUser;
import model.Role;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Set<Role> findByRole(String string);

	//Set<Role> findByRole(String string);

	
	
	
}
