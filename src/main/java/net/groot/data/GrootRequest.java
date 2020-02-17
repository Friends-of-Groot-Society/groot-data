package net.groot.data;
 
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class GrootRequest {

    public GrootRequest() {
		super();
	}

	public GrootRequest(@NotEmpty String title, @NotEmpty @Size(max = 20) String isbn, @NotEmpty String author, @NotEmpty String name, @NotEmpty String type) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.author = author;
		this.name = name;
		this.type = type;
		
	}

	@NotEmpty
    private String title;

    @NotEmpty
    @Size(max = 20)
    private String isbn;

    @NotEmpty
    private String author;

    @NotEmpty
    private String name;
    
    @NotEmpty
    private String type;
    

	public String getAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getIsbn() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
