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

import net.groot.notfound.MediaNotFoundException;
import net.groot.requests.MediaRequest;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MediaController.class)
public class MediaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MediaService mediaService;

    @Captor
    private ArgumentCaptor<MediaRequest> mediaRequestArgumentCaptor;

    @Test
    public void postingANewMediaShouldCreateANewMedia() throws Exception {

        MediaRequest mediaRequest = new MediaRequest();
        mediaRequest.setLocation("Media A.");
        mediaRequest.setUniqueId("1337");
        mediaRequest.setCharacter("Guardians 11");

        when(mediaService.createNewMedia(mediaRequestArgumentCaptor.capture())).thenReturn(1L);

        this.mockMvc
                .perform(post("/api/media")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mediaRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "http://localhost/api/media/1"));

        assertThat(mediaRequestArgumentCaptor.getValue().getLocation(), is("Media A."));
        assertThat(mediaRequestArgumentCaptor.getValue().getUniqueId(), is("1337"));
        assertThat(mediaRequestArgumentCaptor.getValue().getCharacter(), is("Guardians 11"));

    }

    @Test
    public void allMediasEndpointShouldReturnTwoMedias() throws Exception {

        when(mediaService.getAllMedias()).thenReturn(Arrays.asList(     //List.of( // ERROR?
                createMedia(1L, "Guardians 11", "Media A.", "1337"),
                createMedia(2L, "Guardians EE 8", "Media A.", "1338")));

        this.mockMvc
                .perform(get("/api/media"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].character", is("Guardians 11")))
                .andExpect(jsonPath("$[0].location", is("Media A.")))
                .andExpect(jsonPath("$[0].uniqueId", is("1337")))
                .andExpect(jsonPath("$[0].id", is(1)));

    }

    @Test
    public void getMediaWithIdOneShouldReturnAMedia() throws Exception {

        when(mediaService.getMediaById(1L)).thenReturn(createMedia(1L, "Guardians 11", "Media A.", "1337"));

        this.mockMvc
                .perform(get("/api/media/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.character", is("Guardians 11")))
                .andExpect(jsonPath("$.location", is("Media A.")))
                .andExpect(jsonPath("$.uniqueId", is("1337")))
                .andExpect(jsonPath("$.id", is(1)));

    }

    @Test
    public void getMediaWithUnknownIdShouldReturn404() throws Exception {

        when(mediaService.getMediaById(1L)).thenThrow(new MediaNotFoundException("Media with id '1' not found"));

        this.mockMvc
                .perform(get("/api/media/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void updateMediaWithKnownIdShouldUpdateTheMedia() throws Exception {

        MediaRequest mediaRequest = new MediaRequest();
        mediaRequest.setLocation("Media A.");
        mediaRequest.setUniqueId("1337");
        mediaRequest.setCharacter("Guardians 12");

        when(mediaService.updateMedia(eq(1L), mediaRequestArgumentCaptor.capture()))
                .thenReturn(createMedia(1L, "Guardians 12", "Media A.", "1337"));

        this.mockMvc
                .perform(put("/api/media/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mediaRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.character", is("Guardians 12")))
                .andExpect(jsonPath("$.location", is("Media A.")))
                .andExpect(jsonPath("$.uniqueId", is("1337")))
                .andExpect(jsonPath("$.id", is(1)));

        assertThat(mediaRequestArgumentCaptor.getValue().getLocation(), is("Media A."));
        assertThat(mediaRequestArgumentCaptor.getValue().getUniqueId(), is("1337"));
        assertThat(mediaRequestArgumentCaptor.getValue().getCharacter(), is("Guardians 12"));

    }

    @Test
    public void updateMediaWithUnknownIdShouldReturn404() throws Exception {

        MediaRequest mediaRequest = new MediaRequest();
        mediaRequest.setLocation("Media A.");
        mediaRequest.setUniqueId("1337");
        mediaRequest.setCharacter("Guardians 12");

        when(mediaService.updateMedia(eq(42L), mediaRequestArgumentCaptor.capture()))
                .thenThrow(new MediaNotFoundException("The media with id '42' was not found"));

        this.mockMvc
                .perform(put("/api/media/42")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mediaRequest)))
                .andExpect(status().isNotFound());

    }

    private Media createMedia(Long id, String character, String location, String uniqueId) {
        Media media = new Media();
        media.setLocation(location);
        media.setUniqueId(uniqueId);
        media.setCharacter(character);
        media.setId(id);
        return media;
    }

}
