package ecommerce_api.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ecommerce_api.entities.Announcement;
import ecommerce_api.entities.User;
import ecommerce_api.repository.AnnouncementRepository;
import ecommerce_api.repository.UserRepository;

@Path("/announcements")
public class AnnouncementResource {
	 	@EJB
	 	AnnouncementRepository announcementRepository;
	    @EJB
	    UserRepository userRepository;
	    
	    @Context private HttpServletRequest req;
	    
	    @GET
	    @Produces({MediaType.APPLICATION_JSON})
	    public List<Announcement> getAnnouncementsByUser() throws IllegalAccessException{
	    	if(req.getSession().getAttribute("uid")==null)
	    		throw new IllegalAccessException("User not authenticated");
	    	System.out.println("authentifier");
	        return announcementRepository.findAnnouncementsByUserId((Long)req.getSession().getAttribute("uid"));
	    }
	    
	    @GET
		@Path("/{prix}/{title}")
		@Produces({MediaType.APPLICATION_JSON})
		public List<Announcement> getAnnouncementByprix(@PathParam("prix")Float prix,@PathParam("title")String title){
			return announcementRepository.findUserByAnnouncement(prix,title);
		}   
	    
	    @GET
	    @Path("/all")
	    @Produces({MediaType.APPLICATION_JSON})
	    public List<Announcement> getAllannouncements(){
	        return announcementRepository.getAllTheAnnouncement();
	    }
	    
	    @POST
	    @Path("/add")
	    @Consumes("application/json")
	    public void  addAnnouncement(Announcement announcement) throws IllegalAccessException{
	        announcement.setdatePost(new Date());
	        if(req.getSession().getAttribute("uid")==null)
	    		throw new IllegalAccessException("User not authenticated");
	        announcement.setUser((long)req.getSession().getAttribute("uid"));
	        announcementRepository.addAnnouncement(announcement);
	    }
	    
	    @DELETE
		@Path("/{aid}/{uid}")
		public void deleteUser(@PathParam("aid")int aid,@PathParam("uid")Long uid){
			announcementRepository.deleteAnnouncement(aid,uid);
		}
	 
	    @PUT
	    @Path("/update")
		@Consumes("application/json")
	    public void updateAnnouncement(Announcement announcement) throws IllegalAccessException{
	    	if(req.getSession().getAttribute("uid")==null)
	    		throw new IllegalAccessException("User not authenticated");
	    	System.out.println("authentifier");
	        announcementRepository.updateAnnouncement(announcement);
	    }
	    
	    @GET
		@Path("/{id}")
		@Produces({MediaType.APPLICATION_JSON})
		public Announcement getByid(@PathParam("id")int id){
			return announcementRepository.findByid(id);
		}
	    
}
