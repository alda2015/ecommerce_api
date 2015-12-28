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
	
	public void regen(User u){
		Query requete = entityManager.createNativeQuery("select * from User where email='"+u.getEmail()+"'", User.class);
		User user = (User) requete.getSingleResult();
		if( !user.getEmail().equals(u.getEmail()))
			throw new IllegalArgumentException("error");
		if(!user.getQuiz().equals(u.getQuiz()))
			throw new IllegalArgumentException("error");
		//if(!user.getMdp().equals(u.getMdp()))
		String a=u.getMdp();
		user.setMdp(a);
		System.out.println("test  test  test "+ u.getEmail()+" "+ u.getQuiz() +" "+ a);	
		entityManager.merge(user);
	}
	
	public void update(User u,HttpServletRequest req){
//		if(req.getSession().getAttribute("uid")==null)
//			throw new IllegalArgumentException("Authenticate please");
//		if(u.getId() != (Long)req.getSession().getAttribute("uid"))
//			throw new IllegalArgumentException("Not identified");
		if(entityManager.find(User.class, u.getId())==null)
			throw new IllegalArgumentException("Unknown user");
		entityManager.merge(u);
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
		System.out.println("email : "+email+" mdp : "+mdp);
		User user = null;
		try{
			user = (User) requete.getSingleResult();
		}catch(Exception e){
			System.out.println("not found");
			return Response.ok("{\"error\" : \"not found\"}",MediaType.APPLICATION_JSON).build();
		}
		
		if (user.getMdp().equals(mdp)){
			ObjectMapper m = new ObjectMapper();
			try {
				System.out.println();
				req.getSession().setAttribute("uid", user.getId());
				System.out.println("uid : "+req.getSession().getAttribute("uid"));
				return Response.ok(m.writeValueAsString(user), MediaType.APPLICATION_JSON).build();
			} catch (JsonProcessingException e) {
				System.out.println(e.getMessage());
				return Response.ok("{\"error \": \"Error JSON Processing "+e.getMessage()+"\"}",MediaType.APPLICATION_JSON).build();
			}
		}
		System.out.println(mdp+" != "+user.getMdp());
		return Response.ok("{}", MediaType.APPLICATION_JSON).build();
	}

}
