package ecommerce_api.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ecommerce_api.entities.Announcement;

@Stateless
public class AnnouncementRepository {
	
	    
	    @PersistenceContext(unitName = "ecommerce")
	    private EntityManager entityManager;

	    public void addAnnouncement(Announcement announcement){
//	    	System.out.println("Persist announcement");
	        entityManager.persist(announcement);
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
			System.out.println("findAnnouncements for user "+uid);
			Query requete = entityManager.createNativeQuery("select * from Announcement where user="+uid+"", Announcement.class);
			List<Announcement> announcements= (List<Announcement>)requete.getResultList();
			System.out.println(announcements);
			return announcements;
		}
		
		public void deleteAnnouncement(int aid,Long uid){
			Query requete = entityManager.createNativeQuery("select * from Announcement where id="+aid+" And user="+uid+"", Announcement.class);
			Announcement announcement= (Announcement) requete.getSingleResult();
			if(announcement.getId()!=aid)
				throw new IllegalArgumentException("Suppression Impossible");
			entityManager.remove(announcement);
		}
		
		public void updateAnnouncement(Announcement a){
			entityManager.merge(a);
		}
		
		public Announcement findByid(long id) {
			System.out.println("Find by Id");
			Query requete = entityManager.createNativeQuery("select * from Announcement where id="+id, Announcement.class);
			Announcement announcement = (Announcement) requete.getSingleResult();
			return announcement;
		}


		public List<Announcement> findByKeywords(String keywords) {
			String [] keywordsArray = keywords.split(" "); 
			List<Announcement> announcements = new ArrayList<Announcement>();
			for (String k : keywordsArray){
				System.out.println("keyword : "+k);
				Query requete = entityManager.createNativeQuery("SELECT * FROM `announcement` WHERE `title` like '%"+k+"%' ", Announcement.class);
				List<Announcement> l = (List<Announcement>)requete.getResultList();
				announcements.addAll(l);
				System.out.println(l);
			}
			return announcements;
		}
		
	}
