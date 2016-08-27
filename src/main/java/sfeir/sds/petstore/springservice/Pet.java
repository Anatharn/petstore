package sfeir.sds.petstore.springservice;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pet")
public class Pet implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7515248581643706220L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	@Column
	private String category;
	@Column
	private String name;
//	@Column
//	private String[] photoUrl;
//	@Column
//	private String[] tags;
	@Column
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public String[] getPhotoUrl() {
//		return photoUrl;
//	}
//	public void setPhotoUrl(String[] photoUrl) {
//		this.photoUrl = photoUrl;
//	}
//	public String[] getTags() {
//		return tags;
//	}
//	public void setTags(String[] tags) {
//		this.tags = tags;
//	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
