package net.groot.requests;
 
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class MediaRequest {

    public MediaRequest() {
		super();
	}

	public MediaRequest(@NotEmpty String location, @NotEmpty @Size(max = 20) String uniqueId, @NotEmpty String character) {
		super();
		this.location = location;
		this.uniqueId = uniqueId;
		this.character = character;
	}

	@NotEmpty
    private String location;

    @NotEmpty
    @Size(max = 20)
    private String uniqueId;

    @NotEmpty
    private String character;

	public String getCharacter() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUniqueId() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLocation() {
		// TODO Auto-generated method stub
		return null;
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
 
 
	
}
