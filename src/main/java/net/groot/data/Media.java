package net.groot.data;

 
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Media {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String uniqueId;

    @Column(nullable = false)
    private String character;


	@Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String thorinsCompany;
    

    @Column(nullable = false)
    private String quote;
 ///////


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

    public String getUniqueId() {
		return uniqueId;
	}
 
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}


	public String getCharacter() {
		return character;
	}


	public void setCharacter(String character) {
		this.character = character;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getThorinsCompany() {
		return thorinsCompany;
	}


	public void setThorinsCompany(String thorinsCompany) {
		this.thorinsCompany = thorinsCompany;
	}


	public String getQuote() {
		return quote;
	}


	public void setQuote(String quote) {
		this.quote = quote;
	}
     

}
