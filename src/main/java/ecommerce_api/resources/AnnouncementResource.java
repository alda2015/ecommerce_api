package ecommerce_api.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ecommerce_api.entities.Announcement;
import ecommerce_api.repository.AnnouncementRepository;
import ecommerce_api.repository.UserRepository;


import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
//import com.google.gson.Gson;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;

import com.google.gson.Gson;

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
	    	System.out.println(req.getSession().getAttribute("uid"));
	    	if(req.getSession().getAttribute("uid")==null)
	    		throw new IllegalAccessException("User not authenticated");
	        return announcementRepository.findAnnouncementsByUserId((Long)req.getSession().getAttribute("uid"));
	    }
	    
	    @GET
		@Path("/{id}/")
		@Produces({MediaType.APPLICATION_JSON})
		public List<Announcement> getAnnouncementByUid(@PathParam("id")long id){
	    	return announcementRepository.findAnnouncementsByUserId(id);
		}
	    
	    
	    @GET
		@Path("/find/{keywords}")
		@Produces({MediaType.APPLICATION_JSON})
		public List<Announcement> getAnnouncementByKeywords(@PathParam("keywords")String keywords){
			return announcementRepository.findByKeywords(keywords);
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
	        announcement.setDatePost(new Date());
	        if(req.getSession().getAttribute("uid")==null)
	    		throw new IllegalAccessException("User not authenticated");
	        announcement.setUserId((int) req.getSession().getAttribute("uid"));
	        announcementRepository.addAnnouncement(announcement);
	    }
	    
	   
	    @POST
	    @Path("/add/{uid}")
	    @Consumes("application/json")
	    public void  addAnnouncementByUid(Announcement announcement,@PathParam("uid")long uid) throws IllegalAccessException{
	        announcement.setDatePost(new Date());
	        announcement.setUserId((int) uid);
	        announcementRepository.addAnnouncement(announcement);
	    }
	    @POST
	    @Path("/addA/{uid}")
	    @Consumes(MediaType.MULTIPART_FORM_DATA)
	    public void  addAnnouncementByUidImage(Announcement announcement,@PathParam("uid")long uid) throws IllegalAccessException{
	        announcement.setDatePost(new Date());
	        announcement.setUserId((int) uid);
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
//	    	System.out.println(req.getSession().getAttribute("uid"));
//	    	if(req.getSession().getAttribute("uid")==null)
//	    		throw new IllegalAccessException("User not authenticated");
//	    	System.out.println("authentifier");
	        announcementRepository.updateAnnouncement(announcement);
	    }
	    
	    @GET
		@Path("/a/{id}")
		@Produces({MediaType.APPLICATION_JSON})
		public Announcement getByid(@PathParam("id")long id){
			return announcementRepository.findByid(id);
		}
	    
	    @POST
		@Path("/announcement/{announcement}/{uid}")
		@Produces(MediaType.TEXT_PLAIN)
		@Consumes("*/*")
		public void createAnnouncement(
				InputStream stream,@PathParam("announcement")String announcement,@PathParam("uid")int uid) throws IllegalAccessException{
	    	System.out.println("id user "+ uid);
			Gson gson = new Gson(); 
			final Announcement announce = gson.fromJson(announcement, Announcement.class);

			System.out.println("prix -------------->" + announce.getPrix());
			byte[] photo;
			try {
				photo = IOUtils.toByteArray(stream);
				System.out.println(photo); 
				announce.setPhoto(photo);
				announce.setUserId(uid);
				announce.setDatePost(new Date());
				announcementRepository.addAnnouncement(announce);
				System.out.println(announce.getSurface());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}