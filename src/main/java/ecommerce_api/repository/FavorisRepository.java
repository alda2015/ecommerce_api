package ecommerce_api.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ecommerce_api.entities.Announcement;
import ecommerce_api.entities.Favoris;

@Stateless
public class FavorisRepository {

	 @PersistenceContext(unitName = "ecommerce")
      private EntityManager entityManager;
	 
	 public void addfavoris(Favoris favoris){
		 Query requete = entityManager.createNativeQuery("select * from Announcement", Announcement.class);
			Announcement announcement =  (Announcement) requete.getSingleResult();
			if(announcement.getId()!=favoris.getAnnouncement_id())
				throw new IllegalArgumentException("Impossible d'ajouter aux favoris");
			if (announcement.getUserId()!=favoris.getUser_id())
				throw new IllegalArgumentException("Impossible d'ajouter aux favoris");
		 entityManager.persist(favoris);
	 }
}
