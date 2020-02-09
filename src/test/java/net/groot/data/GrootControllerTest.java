package net.groot.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.groot.data.GrootController;
import net.groot.data.GrootRequest;
import net.groot.data.GrootService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; 

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GrootController.class)
public class GrootControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private GrootService grootService;
	
	@Captor
	private ArgumentCaptor<GrootRequest> argumentCaptor;
	
	@Test
	public void testShouldCreateNewGrootInDB() throws JsonProcessingException, Exception {
		
		GrootRequest grootRequest = new GrootRequest();
		grootRequest.setAuthor("Groot A. Dugger");	
		grootRequest.setTitle("Groot yeah!");
		grootRequest.setIsbn("1234");
		
		when(grootService.createNewGroot(argumentCaptor.capture())).thenReturn( 1L );
		
		this.mockMvc.perform(post("/api/groot")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(grootRequest)))
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"))
				.andExpect(header().string("Location",  "http://localhost/api/groot/1"));
		
		assertThat(argumentCaptor.getValue().getAuthor(), is("Groot A. Dugger"));
		assertThat(argumentCaptor.getValue().getTitle(), is("Groot yeah!"));
		assertThat(argumentCaptor.getValue().getIsbn(), is("1234"));
	}
}
