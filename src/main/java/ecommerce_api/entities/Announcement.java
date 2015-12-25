package ecommerce_api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="Announcement")
public class Announcement implements Serializable{
	private static final long serialVersionUID = 9129298985708507881L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@NotNull
	private String title;
	private float prix;
	private float surface;
	private String localisation;
	private String photo;
//	@Size(max=200)
	private String descr;
	@Temporal(TemporalType.DATE)
	private Date datePost;
	
	private long user;
	
	
	public long getUser() {
		return user;
	}
	public void setUser(long l) {
		this.user = l;
	}
	public String getTitle() {
		return title;
	}
	public void setTitre(String titre) {
		this.title = titre;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public float getSurface() {
		return surface;
	}
	public void setSurface(float surface) {
		this.surface = surface;
	}
	public String getLocalisation() {
		return localisation;
	}
	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Date getdatePost() {
		return datePost;
	}
	public void setdatePost(Date datePost) {
		this.datePost = datePost;
	}
	
}