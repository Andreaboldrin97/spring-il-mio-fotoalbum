package org.generation.italy.demo.pojo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Photo {
	
	//Indichiamo le colonne presenti nella tabella ( variabili d'istanza )
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		
		@NotNull
		@NotEmpty(message = "la Photo deve contenere un titolo")
		private String title;
		
		@Column(length = 250)
		private String description;
		
		@NotEmpty(message = "l'immagine non può essere vuota")
		@URL(message = "il path immagine deve essere valido")
		private String url;
		
	
		private String tag;
		
		@NotNull(message = "la visibilità non può essere null")
		private Boolean isVisible;
		
		@OneToMany(mappedBy = "Photo")
		@JsonIgnore
		private Set<Comment> comments;
		
		//creiamo la relazione con le categorie
		@ManyToMany()
		@JoinTable(
				name = "category_Photo",
				joinColumns = @JoinColumn(name = "Photo_id"),
				inverseJoinColumns = @JoinColumn(name = "category_id")
				)
		@JsonIgnore
		private Set<Category> categories;
		
		//COSTRUCTS	
		// indichiamo il costruttore di default
		public Photo() {};

		//creiamo il costruttore
		public Photo(String title, String description, String url, String tag, Boolean isVisible) {
			 
			setTitle(title);
			setDescription(description);
			setUrl(url);	
			setTag(tag);
			setIsVisible(isVisible);
		};
		
		//creiamo il costruttore
		public Photo(String title, String description, String url, String tag, Boolean isVisible, Category...categories) {
					 
			this(title, description, url, tag, isVisible);
			setCategories(new HashSet<>(Arrays.asList(categories)));
		};
		
		//GET&SET
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}

		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
		public String getTag() {
			return tag;
		}
		public void setTag(String tag) {
			this.tag = tag;
		}
		
		public Boolean getIsVisible() {
			return isVisible;
		}
		public void setIsVisible(Boolean isVisible) {
			this.isVisible = isVisible;
		}

		public Set<Category> getCategories() {
			return categories;
		}
		public void setCategories(Set<Category> categories) {
			this.categories = categories;
		}
			
		public Set<Comment> getComments() {
			return comments;
		}
		public void setComments(Set<Comment> comments) {
			this.comments = comments;
		}

		@Override
		public String toString() {
			return "(" + getId() + ") " + getTitle() + " - " 
					+ getDescription() + " - " + getTag() + " - " 
					+ getIsVisible() + " - " + getUrl();
		}
		
		
}
