package net.groot.requests;
 
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class MediaRequest {

    public MediaRequest() {
		super();
	}

	public MediaRequest(@NotEmpty String title, @NotEmpty @Size(max = 20) String isbn, @NotEmpty String author) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.author = author;
	}

	@NotEmpty
    private String title;

    @NotEmpty
    @Size(max = 20)
    private String isbn;

    @NotEmpty
    private String author;

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
	
}
