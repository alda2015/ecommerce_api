package ecommerce_api.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
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
	        User user = userRepository.findUserByEmail("psow@u-bordeaux.fr");
	        return announcementRepository.findAnnouncementsByUser(user);
	    }
	    
	    @POST
	    @Path("/addAnnouncement")
	    @Consumes("application/json")
	    public void  addUser(Announcement announcement){

	        User user = userRepository.findUserByEmail("psow@u-bordeaux.fr");
	        announcement.setCreatedDate(new Date());
	        announcement.setUser(user);
	        announcementRepository.addAnnouncement(announcement);
	    }
}
