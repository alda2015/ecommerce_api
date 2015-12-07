package ecommerce_api.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Savesearch")
public class Savesearch implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -125524585007357426L;
	private int id;
	private float prixMin;
	private float prixMax;
	private float surfaceMin;
	private float surfaceMax;
	private String localisation;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
	User user;

	public float getPrixMin() {
		return prixMin;
	}

	public void setPrixMin(float prixMin) {
		this.prixMin = prixMin;
	}

	public float getPrixMax() {
		return prixMax;
	}

	public void setPrixMax(float prixMax) {
		this.prixMax = prixMax;
	}

	public float getSurfaceMin() {
		return surfaceMin;
	}

	public void setSurfaceMin(float surfaceMin) {
		this.surfaceMin = surfaceMin;
	}

	public float getSurfaceMax() {
		return surfaceMax;
	}

	public void setSurfaceMax(float surfaceMax) {
		this.surfaceMax = surfaceMax;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
