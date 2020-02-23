//
//package net.groot.data.controllerTests;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Arrays;
////import java.util.List;
//
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import net.groot.data.controllers.UserController;
//import net.groot.data.controllers.MediaController;
//import net.groot.data.entities.User;
//import net.groot.data.entities.Media;
//import net.groot.data.notfound.UserNotFoundException;
//import net.groot.data.notfound.MediaNotFoundException;
//import net.groot.data.requests.UserRequest;
//import net.groot.data.requests.MediaRequest;
//import net.groot.data.services.UserService;
//import net.groot.data.services.MediaService;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@MockBean
//	private UserService userService;
//
//	@MockBean
//	private UserService mediaService;
//
//		
//	@Captor
//	private ArgumentCaptor<UserRequest> userRequestArgumentCaptor;
//
//	@Test
//	public void postingANewUserShouldCreateANewUser() throws Exception {
//		Media media = new Media();
////    	media = this.createMedia( 1L, "location,  uniqueId, character, thorinsCompany, quote");
//
//		UserRequest userRequest = new UserRequest();
//		userRequest.setEmail("User A.");
//		userRequest.setPassword("1337");
//		userRequest.setfName("Guardians 11");
//		userRequest.setlName("MyName");
//		userRequest.setMemberSince("TypeXX");
//		userRequest.setGroupType("TypeXX");
//		userRequest.setMedia(this.createMedia(1L, "character", "location", "uniqueId", "thorinsCompany", "quote"));
//
//		when(userService.createNewUser(userRequestArgumentCaptor.capture())).thenReturn(1L);
//
//		this.mockMvc
//				.perform(post("/api/users")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(userRequest)))
//				.andExpect(status().isCreated())
//				.andExpect(header().exists("Location"))
//				.andExpect(header().string("Location", "http://localhost/api/users/1"));
//
//		assertThat(userRequestArgumentCaptor.getValue().getEmail(), is("Guardians 11"));
//		assertThat(userRequestArgumentCaptor.getValue().getPassword(), is("1337"));
//		assertThat(userRequestArgumentCaptor.getValue().getfName(), is("User A."));
//		assertThat(userRequestArgumentCaptor.getValue().getlName(), is("MyName"));
//		assertThat(userRequestArgumentCaptor.getValue().getMemberSince(), is("TypeXX"));
//		assertThat(userRequestArgumentCaptor.getValue().getGroupType(), is("TypeXX"));
//		assertThat(userRequestArgumentCaptor.getValue().getMedia(), is(media));
//
//	}
//
//	@Test
//    public void allUsersEndpointShouldReturnTwoUsers() throws Exception {
//		Media media = new Media();
//		Media media2 = new Media();
////    	media("location,  uniqueId, character, thorinsCompany, quote");
//
//        when(userService.getAllUsers()).thenReturn(Arrays.asList(     //List.of( // ERROR?
//                createUser(1L, "Guardians 11",  "User A.", "1337", "MyName", "TypeXX", "TypeXX", media),
//                createUser(2L, "Guardians 12", "User B.",  "1338", "MyName2", "TypeXX2","TypeXX", media2)));
//
//        this.mockMvc
//                .perform(get("/api/users"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].email", is("Guardians 11")))
//                .andExpect(jsonPath("$[0].password", is("1337")))
//                .andExpect(jsonPath("$[0].fName", is("User A.")))
//                .andExpect(jsonPath("$[0].lName", is("MyName")))
//                .andExpect(jsonPath("$[0].memberSince", is("TypeXX")))
//                .andExpect(jsonPath("$[0].groupType", is("TypeXX")))
//                .andExpect(jsonPath("$[0].media", is(media)));
//
//    }
//
//	@Test
//	public void getUserWithIdOneShouldReturnAUser() throws Exception {
//		Media media = new Media();
//		when(userService.getUserById(1L))
//				.thenReturn(createUser(1L, "Guardians 11", "User A.", "1337", "MyName", "TypeXX",  "groupType",  media ));
//
//		this.mockMvc.perform(get("/api/users/1")).andExpect(status().isOk())
//				.andExpect(content().contentType("application/json")).andExpect(jsonPath("$.email", is("Guardians 11")))
//				.andExpect(jsonPath("$.password", is("User A."))).andExpect(jsonPath("$.fName", is("1337")))
//				.andExpect(jsonPath("$.lName", is(1)));
//
//	}
//
//	@Test
//	public void getUserWithUnknownIdShouldReturn404() throws Exception {
//
//		when(userService.getUserById(1L)).thenThrow(new UserNotFoundException("User with id '1' not found"));
//
//		this.mockMvc.perform(get("/api/users/1")).andExpect(status().isNotFound());
//
//	}
//
//	@Test
//	public void updateUserWithKnownIdShouldUpdateTheUser() throws Exception {
//		
//		Media media = new Media();
////    	media("location,  uniqueId, character, thorinsCompany, quote");
//		
//		UserRequest userRequest = new UserRequest();
//
//		userRequest.setEmail("Guardians 12");
//		userRequest.setPassword("1337");
//		userRequest.setfName("User A.");
//		userRequest.setlName("MyName");
//		userRequest.setMemberSince("9-3-3333");
//		userRequest.setGroupType("MyName");
//		userRequest.setMedia(media);
//
//		when(userService.updateUser(eq(1L), userRequestArgumentCaptor.capture()))
//				.thenReturn(createUser(1L, "Guardians 12", "User A.", "1337", "MyName", "TypeXX", "groupType", media));
//
//		this.mockMvc
//				.perform(put("/api/users/1").contentType(MediaType.APPLICATION_JSON)
//						.content(objectMapper.writeValueAsString(userRequest)))
//				.andExpect(status().isOk()).andExpect(content().contentType("application/json"))
//				.andExpect(jsonPath("$.email", is("Guardians 12")))
//				.andExpect(jsonPath("$.password", is("User A.")))
//				.andExpect(jsonPath("$.fName", is("1337")))
//				.andExpect(jsonPath("$.lName", is("MyName")));
//
//		assertThat(userRequestArgumentCaptor.getValue().getEmail(), is("Guardians 12"));
//		assertThat(userRequestArgumentCaptor.getValue().getPassword(), is("User A."));
//		assertThat(userRequestArgumentCaptor.getValue().getfName(), is("1337"));
//		assertThat(userRequestArgumentCaptor.getValue().getfName(), is("MyName"));
//
//	}
//
//	@Test
//	public void updateUserWithUnknownIdShouldReturn404() throws Exception {
//
//		Media media = new Media();
////    	media("location,  uniqueId, character, thorinsCompany, quote");
//		
//		UserRequest userRequest = new UserRequest();
//
//		userRequest.setEmail("Guardians 12");
//		userRequest.setPassword("1337");
//		userRequest.setfName("User A.");
//		userRequest.setlName("1337");
//		userRequest.setMemberSince("User A.");
//		userRequest.setGroupType("1337");
//		userRequest.setMedia(media);
//
//		when(userService.updateUser(eq(42L), userRequestArgumentCaptor.capture()))
//				.thenThrow(new UserNotFoundException("The user with id '42' was not found"));
//
//		this.mockMvc.perform(put("/api/user/42").contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(userRequest))).andExpect(status().isNotFound());
//
//	}
//
//	private User createUser(Long id, String email, String password, String fName, String lName, String memberSince, String groupType, Media media ) {
//
////		Media media = new Media();
////    	media("location,  uniqueId, character, thorinsCompany, quote");
//		User user = new User();
//
//		user.setId(id);
//		user.setEmail(email);
//		user.setPassword(password);
//		user.setfName(fName);
//		user.setlName(lName);
//		user.setMemberSince(memberSince);
//		user.setGroupType(lName);
//		user.setMedia(media);
//		return user;
//	}
//	 private Media createMedia(Long id, String character, String location, String uniqueId, String thorinsCompany, String quote) {
//	        Media media = new Media();
//	        media.setLocation(location);
//	        media.setUniqueId(uniqueId);
//	        media.setCharacter(character);
//	        media.setThorinsCompany(thorinsCompany);
//	        media.setQuote(quote);
//	        media.setId(id);
//	        return media;
//	    }
//
//}
