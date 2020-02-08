package net.groot.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import net.groot.data.GrootController;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GrootController.class)
public class GrootControllerTest {

	@Test
	public void testShouldCreateNewGrootInDB() {
		
	}
}
