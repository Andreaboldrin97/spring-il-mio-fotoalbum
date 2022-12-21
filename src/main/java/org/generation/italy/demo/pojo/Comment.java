package org.generation.italy.demo.pojo;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@NotNull
	@NotEmpty
	@Lob
	private String text;
	
	@ManyToOne
	@JoinColumn(name="Photo_id", nullable=false)
	@JsonIgnore
	private Photo Photo;
	
	//COSTRUCTS	
	// indichiamo il costruttore di default
	public Comment() {};
	
	// costruttore custum
	public Comment(String name, String text, Photo Photo) {
		
		setName(name);
		setText(text);
		setPhoto(Photo);
	}
	
	
	//GET&SET
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public Photo getPhoto() {
		return Photo;
	}
	public void setPhoto(Photo Photo) {
		this.Photo = Photo;
	};
	
	@Override
	public String toString() {
		return "(" + getId() + ") " + getName() + ": " + getText(); 
	}
	
	@Override
	public int hashCode() {
		return getId();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Category)) return false;
		
		return obj.hashCode() == hashCode();
	}
	
}
