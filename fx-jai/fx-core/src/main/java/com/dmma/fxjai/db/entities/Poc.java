package com.dmma.fxjai.db.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**This entity will be used only as a test
 * to show full java development process 
 * */

@Entity
@Table(name = "poc")
public class Poc {
	
	@Id()
	@Column(name = "id")
	@GeneratedValue
	private Integer id;
	
	@Column 
	private String text;
	
	@Column 
	private Date created;
	
	
	public Poc() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
}
