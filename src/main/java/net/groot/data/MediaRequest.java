package net.groot.data;
 
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class MediaRequest {
	
	@NotEmpty
    private String location;

    @NotEmpty
    @Size(max = 80)
    private String uniqueId;

    @NotEmpty
    private String character;

    private @NotEmpty String thorinsCompany;

	private @NotEmpty String quote;

	public MediaRequest() {
		super();
	}

	public MediaRequest(@NotEmpty String location, @NotEmpty @Size(max = 80) String uniqueId, @NotEmpty String character, @NotEmpty String thorinsCompany, @NotEmpty String quote) {
		super();
		this.location = location;
		this.uniqueId = uniqueId;
		this.character = character;
		this.thorinsCompany = thorinsCompany;
		this.quote = quote;
	}
 

	public String getCharacter() { 
		return character;
	}

	public String getUniqueId() { 
		return uniqueId;
	}

	public void setThorinsCompany(String thorinsCompany) {
		this.thorinsCompany = thorinsCompany;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getLocation() { 
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getThorinsCompany() { 
		return thorinsCompany;
	}
	public String getQuote() { 
		return quote;
	}

  
	
}
