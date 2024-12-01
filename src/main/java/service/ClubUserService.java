package service;


import model.ClubUser;
import repository.ClubUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubUserService {

    @Autowired
    private ClubUserRepository clubUserRepository;

    public ClubUser addClubUser(ClubUser clubUser) {
        // Additional logic can be added here (e.g., validation, password encryption)
        return clubUserRepository.save(clubUser);
    }
}

