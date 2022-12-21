package org.generation.italy.demo.pojo;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotEmpty(message = "il nome non può essere vuoto")
	private String name;
	
	@ManyToMany(mappedBy = "categories")
	@JsonIgnore
	private List<Photo> Photo;
	
	//COSTRUCTS	
	// indichiamo il costruttore di default
	public Category () {};

	//creiamo il costruttore
	public Category (String name) {
		setName(name);
	};
	
	//creiamo il costruttore + Photo
	public Category (String name, Photo...Photos) {
		this(name);
		setPhoto(Arrays.asList(Photos));
	};
	
	
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

	public List<Photo> getPhoto() {
		return Photo;
	}
	public void setPhoto(List<Photo> Photo) {
		this.Photo = Photo;
	}
	
	@Override
	public String toString() {
		return "(" + getId() + ") " + getName(); 
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
