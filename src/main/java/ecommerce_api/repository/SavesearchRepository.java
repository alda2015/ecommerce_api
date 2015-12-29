package ecommerce_api.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ecommerce_api.entities.Savesearch;

@Stateless
public class SavesearchRepository {

	 @PersistenceContext(unitName = "ecommerce")
	    private EntityManager entityManager;
	 
	 public void addsavesearch(Savesearch savesearch){
	        entityManager.persist(savesearch);
	    }
	 
	 public void deletesearch(int idsave, long iduser){
			Query requete = entityManager.createNativeQuery("select * from Savesearch where id='"+idsave+"' And user_id='"+iduser+"'", Savesearch.class);
			Savesearch savesearch = (Savesearch) requete.getSingleResult();
			if(savesearch.getId()!=idsave)
				throw new IllegalArgumentException("Suppression Impossible");
			entityManager.remove(savesearch);
		}
	
//	 public Savesearch findByid(int id) {
//			Query requete = entityManager.createNativeQuery("select * from Savesearch where id='"+id+"'", Savesearch.class);
//			Savesearch savesearch =  (Savesearch) requete.getSingleResult();
//			return savesearch;
//		}

		@SuppressWarnings("unchecked")
		public List<Savesearch> findSavesearchByUserId(Long uid) {
			Query requete = entityManager.createNativeQuery("select * from Savesearch where user_id="+uid+"", Savesearch.class);
			List<Savesearch> savesearchs = requete.getResultList();
			return savesearchs;
		}	
}
