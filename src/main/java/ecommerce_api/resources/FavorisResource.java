package ecommerce_api.resources;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import ecommerce_api.entities.Favoris;
import ecommerce_api.repository.FavorisRepository;

@Path("/favoris")
public class FavorisResource {

	@EJB
	FavorisRepository favorisRepository;
	
	@Context private HttpServletRequest req;
	
//	  @POST
//	  @Path("/{iduser}/{idannouncement}")
//	  @Consumes("application/json")
//	    public void addFavoris(@PathParam("iduser")int iduser,@PathParam("idannouncement")int idannouncement) throws IllegalAccessException{
//	        if(req.getSession().getAttribute("uid")==null)
//	    		throw new IllegalAccessException("User not authenticated");
//	       Favoris favoris = null;
//	       favoris.setUser_id((int) req.getSession().getAttribute("uid"));
//	       favorisRepository.addfavoriss(iduser, idannouncement);
//	    }
	  
	  @POST
	  @Path("/addFavoris")
	  @Consumes("application/json")
	    public void addFavoris(Favoris favoris) throws IllegalAccessException{
	        if(req.getSession().getAttribute("uid")==null)
	    		throw new IllegalAccessException("User not authenticated");
	       favoris.setUser_id((long) req.getSession().getAttribute("uid"));
	       favorisRepository.addfavoris(favoris);
	    }
}
