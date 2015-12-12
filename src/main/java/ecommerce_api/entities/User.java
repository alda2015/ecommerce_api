package ecommerce_api.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="User")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8897117888817851683L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@NotNull
	@Column(name = "email", length = 200)
	private String email;
	@Size(max=30)
	private String firstName;
	@Column(length=30)
	private String lastName;
	@Size(min=8)
	private String mdp;
	private String tel;
	private String address;
	private boolean admin;
	
	@OneToMany(mappedBy="user")
	List<Announcement> announcements;
	
	@OneToMany(mappedBy="user")
	List<Savesearch> savesearchs;
	

	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public List<Savesearch> getSavesearchs() {
		return savesearchs;
	}
	public void setSavesearchs(List<Savesearch> savesearchs) {
		this.savesearchs = savesearchs;
	}
	public long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public List<Announcement> getAnnouncements() {
		return announcements;
	}
	public void setAnnouncements(List<Announcement> announcements) {
		this.announcements = announcements;
	}
	
}
