package ecommerce_api.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	    public List<Announcement> getAnnouncementsByUser(){
	    	System.out.println(req.getSession().getAttribute("uid"));
	        User user = userRepository.findUserByEmail("landry@gmail.com");
	        return announcementRepository.findAnnouncementsByUser(user);
	    }
	    
	    @GET
		@Path("/{prix}/{title}")
		@Produces({MediaType.APPLICATION_JSON})
		public List<Announcement> getAnnouncementByprix(@PathParam("prix")Float prix,@PathParam("title")String title){
			return announcementRepository.findUserByAnnouncement(prix,title);
		}
	    
	    /*a revoir*/
	    @GET
	    @Path("/all")
	    @Produces({MediaType.APPLICATION_JSON})
	    public List<Announcement> getAllannouncements(){
	        return announcementRepository.getAllTheAnnouncement();
	    }
	    
	    @POST
	    @Path("/addAnnouncement")
	    @Consumes("application/json")
	    public void  addUser(Announcement announcement){
	        User user = userRepository.findUserByEmail("psow@u-bordeaux.fr");
	        announcement.setdatePost(new Date());
	        announcement.setUser(user);
	        announcementRepository.addAnnouncement(announcement);
	    }
}
