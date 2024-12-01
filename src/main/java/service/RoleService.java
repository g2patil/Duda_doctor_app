package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import model.Role;
import repository.RoleRepository;


public interface RoleService {
    public static final RoleRepository roleRepository = null;
	
    Role findByName(String name);
     

    public default List<Role> getRolesByNameLike(String name) {
        return roleRepository.findByRoleContainingIgnoreCase(name);
    }
    
    
}