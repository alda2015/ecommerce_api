package ecommerce_api.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ecommerce_api.entities.Savesearch;
import ecommerce_api.repository.SavesearchRepository;

@Path("/saveseach")
public class SavesearchResource {

    @EJB
    SavesearchRepository savesearchRepository;
    
    @Context private HttpServletRequest req;
		
	  @POST
	  @Path("/addSearch")
	  @Consumes("application/json")
	    public void addSearch(Savesearch savesearch) throws IllegalAccessException{
	        if(req.getSession().getAttribute("uid")==null)
	    		throw new IllegalAccessException("User not authenticated");
	        savesearch.setUser_id((long) req.getSession().getAttribute("uid"));
	        savesearchRepository.addsavesearch(savesearch);
	    }
	  
	  @DELETE
		@Path("/{idsave}/{iduser}")
		public void deleteUser(@PathParam("idsave")int idsave,@PathParam("iduser")Long iduser){
			savesearchRepository.deletesearch(idsave,iduser);
		}
	  
	  @GET
	  @Path("/{id}/")
	  @Produces({MediaType.APPLICATION_JSON})
	  public List<Savesearch> getSavesearchByUid(@PathParam("id")long id){
	   	return savesearchRepository.findSavesearchByUserId(id);
	  }
	  
//	  @GET
//	  @Path("/{saveid}")
//	  @Produces({MediaType.APPLICATION_JSON})
//	  public Savesearch getByid(@PathParam("id")int id){
//		 return savesearchRepository.findByid(id);
//	  }
}
