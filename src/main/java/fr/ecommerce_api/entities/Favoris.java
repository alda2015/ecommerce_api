package fr.ecommerce_api.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="favoris")
public class Favoris implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	//private long user_id;
	private int announcement_id;
	
	private long user_id;
	
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long l) {
		this.user_id = l;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public int getAnnouncement_id() {
		return announcement_id;
	}
	public void setAnnouncement_id(int announcement_id) {
		this.announcement_id = announcement_id;
	}

	
}
