package ecommerce_api.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public Response login(String email, String mdp, HttpServletRequest req) {
		Query requete = entityManager.createNativeQuery("select * from User where email='"+email+"'", User.class);
		User user = null;
		try{
			user = (User) requete.getSingleResult();
		}catch(Exception e){
			System.out.println("not found");
			return Response.ok("{}",MediaType.APPLICATION_JSON).build();
		}
		
		if (user.getMdp().equals(mdp)){
			ObjectMapper m = new ObjectMapper();
			try {
				req.getSession().setAttribute("uid", user.getId());
				return Response.ok(m.writeValueAsString(user), MediaType.APPLICATION_JSON).build();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(mdp+" != "+user.getMdp());
		return Response.ok("{}", MediaType.APPLICATION_JSON).build();
	}
}
