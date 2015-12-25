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
	    	System.out.println("Persist announcement");
	        entityManager.persist(announcement);
	    }

	    public List<Announcement> findAnnouncementsByUser(User user) {
	        Query requete = entityManager.createQuery(JPQL_SELECT_PAR_EMAIL);
	        requete.setParameter(PARAM_USER, user);
	        @SuppressWarnings("unchecked")
	        List<Announcement> announcements = (List<Announcement>)requete.getResultList();
	        return announcements;
	    }
	    
		@SuppressWarnings("unchecked")
		public List<Announcement> getAllTheAnnouncement(){
			Query requete = entityManager.createNativeQuery("select * from Announcement", Announcement.class);
			System.out.println("getR");
			List<Announcement> announcements= (List<Announcement>)requete.getResultList();
			System.out.println("notting ret ? "+announcements);
			return announcements;

		}
		
		public List<Announcement> findUserByAnnouncement(Float prix,String title) {
			Query requete = entityManager.createNativeQuery("select * from Announcement where prix='"+prix+"' And title='"+title+"'", Announcement.class);
			@SuppressWarnings("unchecked")
			List<Announcement> announcements= (List<Announcement>)requete.getResultList();
			return announcements;
		}


		@SuppressWarnings("unchecked")
		public List<Announcement> findAnnouncementsByUserId(Long uid) {
			Query requete = entityManager.createNativeQuery("select * from Announcement where user_id='"+uid+"'", Announcement.class);
			List<Announcement> announcements= (List<Announcement>)requete.getResultList();
			return announcements;
		}
		
		public void deleteAnnouncement(int aid,Long uid){
			Query requete = entityManager.createNativeQuery("select * from Announcement where id='"+aid+"' And user_id='"+uid+"'", Announcement.class);
			Announcement announcement= (Announcement) requete.getSingleResult();
			if(announcement.getId()!=aid)
				throw new IllegalArgumentException("Suppression Impossible");
			entityManager.remove(announcement);
		}
		
		public void updateAnnouncement(Announcement a){
			entityManager.merge(a);
		}

	}
