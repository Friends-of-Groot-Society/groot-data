package net.groot.data.requests;
 
import lombok.Data;
import net.groot.data.entities.Media;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserRequest {

	public UserRequest() {
		super();
	}
 
    public UserRequest(String tokenId, @NotEmpty String email, @NotEmpty String password, @NotEmpty String fName, @NotEmpty String lName,
    		String memberSince, String groupType, Media media) {
    	super();
    	this.tokenId = tokenId;
    	this.email = email;
    	this.password = password;
    	this.fName = fName;
    	this.lName = lName;
    	this.memberSince = memberSince;
    	this.groupType = groupType;
    	this.media = media;
    }

	private String tokenId; 
	
	@NotEmpty
    private String email; 

	@NotEmpty 
    private String password;

    @NotEmpty
    private String fName;

    @NotEmpty
    private String lName;
     
    private String memberSince;

    private String groupType;

    private  Media media;

    //

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(String memberSince) {
		this.memberSince = memberSince;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

     
	
}
