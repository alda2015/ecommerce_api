package ecommerce_api.resources;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ecommerce_api.entities.User;
import ecommerce_api.repository.UserRepository;

@Path("/users")
public class UserResource {
	@EJB
	UserRepository userRepository;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<User> getAllUsers(){
		return userRepository.getAllTheUsers();
	}
	
	@GET
	@Path("/{email}")
	@Produces({MediaType.APPLICATION_JSON})
	public User getUserByMail(@PathParam("email")String email){
		return userRepository.findUserByEmail(email);
	}
	
	@POST
	@Path("/addUser")
	@Consumes("application/json")
	public void  addUser(User user){
		System.out.println(" inscription desire" );
		userRepository.addUser(user);
	}
	
//	@POST
//	@Path("/inscription")
//	@Consumes("application/json")
//	public void  inscription(@QueryParam("email")String email,@QueryParam("mdp")String mdp,@QueryParam("admin")Boolean admin){
//		User user = new User();
//		user.setEmail(email);
//		user.setMdp(mdp);
//		user.setAdmin(admin);
//		System.out.println("email"+user.getEmail()+"mdp"+user.getMdp()+"admin"+user.isAdmin());
//		userRepository.addUser(user);
//	}
	
	@POST
	@Path("/inscription")
	@Consumes("application/json")
	public void inscription(User user){
//		User user = new User();
		System.out.println("email ="+user.getEmail()+"mdp ="+user.getMdp()+"admin ="+user.isAdmin());
		userRepository.inscription(user.getEmail(),user.getMdp(),user.isAdmin());
	}
	
	@DELETE
	@Path("/{email}")
	public void deleteUser(@PathParam("email")String email){
		userRepository.deleteUser(email);
	}
}
