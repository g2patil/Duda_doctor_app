package service;

import model.Role;

public interface RoleService {
    // Method to find a Role by its name
    Role findByName(String name);
}