package ecommerce_api.resources;

import javax.ws.rs.Path;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
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
	    
	    @GET
	    @Produces({MediaType.APPLICATION_JSON})
	    public List<Announcement> getAnnouncementsByUser(){
	        User user = userRepository.findUserByEmail("dez@gmail.com");
	        return announcementRepository.findAnnouncementsByUser(user);
	    }
	    
	    @POST
	    @Path("/addAnnouncement")
	    @Consumes("application/json")
	    public void  addUser(Announcement announcement){
	        User user = userRepository.findUserByEmail("dez@gmail.com");
	        announcement.setCreatedDate(new Date());
	        announcement.setUser(user);
	        announcementRepository.addAnnouncement(announcement);
	    }
}
