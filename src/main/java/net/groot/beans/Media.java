package net.groot.beans;

import lombok.Data;
//import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//import org.springframework.stereotype.Component;

//@Component
@Data
@Entity
//@Table(name="medias")
public class Media { //implements Serializable 
		//	private static final long SerialVersionUID = 1L;
		//	@Id
		//	@GeneratedValue(strategy=GenerationType.IDENTITY)
		//	@Column(name="media_id")
		//	private int mediaId;
	
		@Id
	    @GeneratedValue
	    private Long id;

	    @Column(nullable = false, unique = true)
	    private String isbn;

	    @Column(nullable = false)
	    private String title;

	    @Column(nullable = false)
	    private String author;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}
	     
	
}
