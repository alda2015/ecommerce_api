package ecommerce_api.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
		userRepository.addUser(user);
	}
	
	@Context private HttpServletRequest req;
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User u){
		return userRepository.login(u.getEmail(),u.getMdp(),req);
	}
	
	
	@DELETE
	@Path("/{email}")
	public void deleteUser(@PathParam("email")String email){
		userRepository.deleteUser(email);
	}
}
