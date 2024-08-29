package service;

import model.Patient;
import org.springframework.data.jpa.domain.Specification;

public class PatientSpecification {

    public static Specification<Patient> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                firstName != null ? criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%") : null;
    }

    public static Specification<Patient> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                lastName != null ? criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%") : null;
    }

    public static Specification<Patient> hasGender(String gender) {
        return (root, query, criteriaBuilder) ->
                gender != null ? criteriaBuilder.equal(root.get("gender"), gender) : null;
    }

    public static Specification<Patient> hasContactNumber(String contactNumber) {
        return (root, query, criteriaBuilder) ->
                contactNumber != null ? criteriaBuilder.equal(root.get("contactNumber"), contactNumber) : null;
    }

    public static Specification<Patient> hasEmail(String email) {
        return (root, query, criteriaBuilder) ->
                email != null ? criteriaBuilder.equal(root.get("email"), email) : null;
    }
}