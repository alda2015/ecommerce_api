package ecommerce_api.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ecommerce_api.entities.User;

@Stateless
public class UserRepository {
	@SuppressWarnings("unused")
	private static final String JPQL_SELECT_PAR_EMAIL = "SELECT u FROM User u WHERE u.email=:email";
	@SuppressWarnings("unused")
	private static final String PARAM_EMAIL = "email";
	@PersistenceContext(unitName = "ecommerce")
	private EntityManager entityManager;

	public void addUser(User user){
		entityManager.persist(user);
	}
	
	public void inscription(String email,String mdp,Boolean admin){
		Query requete = entityManager.createNativeQuery("select * from User where email='"+email+"'", User.class);
		System.out.println(" inscription desire" );
		User user = (User)requete.getSingleResult();
//		System.out.println(" inscription desire"+user );
//		if(user.getEmail().equals(email)){
//			System.out.println("Email deja utiliser");
//		}
		user.setEmail(email);
		user.setMdp(mdp);
		user.setAdmin(admin);
		entityManager.persist(user);
	}

	public User findUserByEmail(String email) {
		Query requete = entityManager.createNativeQuery("select * from User where email='"+email+"'", User.class);
		User user = (User) requete.getSingleResult();
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAllTheUsers(){
		return entityManager.createNativeQuery("select * from User", User.class)
                .getResultList();
	}
	
	public void deleteUser(String email){
		Query requete = entityManager.createNativeQuery("select * from User where email='"+email+"'", User.class);
		User user = (User) requete.getSingleResult();
		entityManager.remove(user);
	}
}
