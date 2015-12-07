package ecommerce_api.repository;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ecommerce_api.entities.Announcement;
import ecommerce_api.entities.User;

@Stateless
public class AnnouncementRepository {
	
	    private static final String JPQL_SELECT_PAR_EMAIL = "SELECT a FROM Announcement a WHERE a.user=:user";
	    private static final String PARAM_USER = "user";
	    
	    @PersistenceContext(unitName = "ecommerce")
	    private EntityManager entityManager;

	    public void addAnnouncement(Announcement announcement){
	        entityManager.persist(announcement);
	    }

	    public List<Announcement> findAnnouncementsByUser(User user) {
	        Query requete = entityManager.createQuery(JPQL_SELECT_PAR_EMAIL);
	        requete.setParameter(PARAM_USER, user);
	        @SuppressWarnings("unchecked")
	        List<Announcement> announcements = (List<Announcement>)requete.getResultList();
	        return announcements;
	    }
	}
