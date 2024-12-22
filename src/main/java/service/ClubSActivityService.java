package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import model.club_s_activity;
import repository.ClubSActivityRepository;
import repository.RoleRepository;
import repository.RoleSubActivityRepository;
import repository.club_s_activityRepository;

@Service
public class ClubSActivityService {

    @Autowired
    private club_s_activityRepository clubSActivityRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleSubActivityRepository roleSubActivityRepository;

	private club_s_activityRepository repository;
	
	 @Autowired
	    private 
	ClubSActivityRepository clubSActivityRepository1;

    @Transactional
    public club_s_activity addSubActivity(club_s_activity subActivity) {
        // Save the new sub-activity
        club_s_activity savedSubActivity = clubSActivityRepository.save(subActivity);

        // Fetch all roles or specific roles based on your requirements
      /*  List<Role> roles = roleRepository.findAll();

        // Create entries in the RoleSubActivity table for each role
        for (Role role : roles) {
            RoleSubActivity roleSubActivity = new RoleSubActivity();
            roleSubActivity.setRole(role);
            roleSubActivity.setSubActivity(savedSubActivity);
            roleSubActivity.setPaid(false); // Default to free; adjust as per your logic
            roleSubActivity.setStartDate(LocalDate.now()); // Default start date; adjust if needed
            roleSubActivityRepository.save(roleSubActivity);
        }
*/
        return savedSubActivity;
    }
    
	/*public List<club_s_activity> findByMainActivityId(Long id) {
		// TODO Auto-generated method stub
		//return null;
		 return clubSActivityRepository1.findByMainActivityId(id);

	}*/
    public List<club_s_activity> getSubActivities(Long mainActivityId) {
        return clubSActivityRepository1.findByMainActivityId(mainActivityId);
    }
 
 
    
    
}
