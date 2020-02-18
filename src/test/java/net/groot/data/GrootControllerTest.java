package net.groot.data;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
//import java.util.List;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import net.groot.notfound.GrootNotFoundException;

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
    private ArgumentCaptor<GrootRequest> grootRequestArgumentCaptor;

    @Test
    public void postingANewGrootShouldCreateANewGroot() throws Exception {

        GrootRequest grootRequest = new GrootRequest();
        grootRequest.setAuthor("Groot A.");
        grootRequest.setIsbn("1337");
        grootRequest.setTitle("Guardians 11");
        grootRequest.setName("MyName");
        grootRequest.setType("TypeXX");

        when(grootService.createNewGroot(grootRequestArgumentCaptor.capture())).thenReturn(1L);

        this.mockMvc
                .perform(post("/api/groot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(grootRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "http://localhost/api/groot/1"));

        assertThat(grootRequestArgumentCaptor.getValue().getTitle(), is("Guardians 11"));
        assertThat(grootRequestArgumentCaptor.getValue().getIsbn(), is("1337"));
        assertThat(grootRequestArgumentCaptor.getValue().getAuthor(), is("Groot A."));
        assertThat(grootRequestArgumentCaptor.getValue().getName(), is("MyName"));
        assertThat(grootRequestArgumentCaptor.getValue().getType(), is("TypeXX"));


    }

    @Test
    public void allGrootsEndpointShouldReturnTwoGroots() throws Exception {

        when(grootService.getAllGroots()).thenReturn(Arrays.asList(     //List.of( // ERROR?
                createGroot(1L, "Guardians 11",  "Groot A.", "1337", "MyName", "TypeXX"),
                createGroot(2L, "Guardians 12", "Groot B.",  "1338", "MyName2", "TypeXX2")));

        this.mockMvc
                .perform(get("/api/groot"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Guardians 11")))
                .andExpect(jsonPath("$[0].isbn", is("1337")))
                .andExpect(jsonPath("$[0].author", is("Groot A.")))
                .andExpect(jsonPath("$[0].name", is("MyName")))
                .andExpect(jsonPath("$[0].type", is("TypeXX")));

    }

    @Test
    public void getGrootWithIdOneShouldReturnAGroot() throws Exception {

        when(grootService.getGrootById(1L)).thenReturn(createGroot(1L, "Guardians 11", "Groot A.", "1337", "MyName", "TypeXX"));

        this.mockMvc
                .perform(get("/api/groot/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.title", is("Guardians 11")))
                .andExpect(jsonPath("$.author", is("Groot A.")))
                .andExpect(jsonPath("$.isbn", is("1337")))
                .andExpect(jsonPath("$.id", is(1)));

    }

    @Test
    public void getGrootWithUnknownIdShouldReturn404() throws Exception {

        when(grootService.getGrootById(1L)).thenThrow(new GrootNotFoundException("Groot with id '1' not found"));

        this.mockMvc
                .perform(get("/api/groot/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void updateGrootWithKnownIdShouldUpdateTheGroot() throws Exception {

        GrootRequest grootRequest = new GrootRequest();

        grootRequest.setTitle("Guardians 12");
        grootRequest.setIsbn("1337");
        grootRequest.setAuthor("Groot A.");
        grootRequest.setName("MyName");
        grootRequest.setType("TypeXX");

        when(grootService.updateGroot(eq(1L), grootRequestArgumentCaptor.capture()))
                .thenReturn(createGroot(1L, "Guardians 12", "Groot A.", "1337","MyName", "TypeXX"));

        this.mockMvc
                .perform(put("/api/groot/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(grootRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.title", is("Guardians 12")))
                .andExpect(jsonPath("$.author", is("Groot A.")))
                .andExpect(jsonPath("$.isbn", is("1337")))
                .andExpect(jsonPath("$.id", is(1)));

        assertThat(grootRequestArgumentCaptor.getValue().getAuthor(), is("Groot A."));
        assertThat(grootRequestArgumentCaptor.getValue().getIsbn(), is("1337"));
        assertThat(grootRequestArgumentCaptor.getValue().getTitle(), is("Guardians 12"));

    }

    @Test
    public void updateGrootWithUnknownIdShouldReturn404() throws Exception {

        GrootRequest grootRequest = new GrootRequest();

        grootRequest.setTitle("Guardians 12");
        grootRequest.setIsbn("1337");
        grootRequest.setAuthor("Groot A.");
        grootRequest.setName("1337");
        grootRequest.setType("Groot A.");

        when(grootService.updateGroot(eq(42L), grootRequestArgumentCaptor.capture()))
                .thenThrow(new GrootNotFoundException("The groot with id '42' was not found"));

        this.mockMvc
                .perform(put("/api/groot/42")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(grootRequest)))
                .andExpect(status().isNotFound());

    }

    private Groot createGroot(Long id, String title, String author, String isbn, String name, String type) {
        Groot groot = new Groot();

        groot.setId(id);
        groot.setTitle(title);
        groot.setIsbn(isbn);
        groot.setAuthor(author);
        groot.setName(name);
        groot.setType(type);
        return groot;
    }

}
