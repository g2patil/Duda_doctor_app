package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.club_s_activity;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ClubSActivityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<club_s_activity> findByMainActivityId(Long mainActivityId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<club_s_activity> query = cb.createQuery(club_s_activity.class);
        Root<club_s_activity> root = query.from(club_s_activity.class);
        System.out.println("m_id|"+mainActivityId+"|");
        System.out.println("root |"+root.toString()+"|");
        query.select(root)
            .where(cb.equal(root.get("club_m_activity").get("id"), mainActivityId));
       System.out.println("#"+root.get("club_m_activity").get("id").toString()+"#");
      
       
       return entityManager.createQuery(query).getResultList();
    }
}
