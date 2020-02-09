package net.groot.data;
 
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GrootControllerIT {

    @LocalServerPort
    int randomServerPort;

    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUp() {
        this.testRestTemplate = new TestRestTemplate();
    }

    @Test
    public void deletingKnownEntityShouldReturn404AfterDeletion() {
        long grootId = 1;
        String baseUrl = "http://localhost:" + randomServerPort;

        ResponseEntity<JsonNode> firstResult = this.testRestTemplate
                .getForEntity(baseUrl + "/api/groot/" + grootId, JsonNode.class);

        assertThat(firstResult.getStatusCode(), is(HttpStatus.OK));

        this.testRestTemplate.delete(baseUrl + "/api/groot/" + grootId);

        ResponseEntity<JsonNode> secondResult = this.testRestTemplate
                .getForEntity(baseUrl + "/api/groot/" + grootId, JsonNode.class);

        assertThat(secondResult.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}