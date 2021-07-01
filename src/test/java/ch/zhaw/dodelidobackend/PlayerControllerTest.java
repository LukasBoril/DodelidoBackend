package ch.zhaw.dodelidobackend;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.zhaw.dodelidobackend.controller.PlayerController;
import ch.zhaw.dodelidobackend.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Test class for PlayerController
 * @author Kaltrim Bajrami
 * @version 01.07.2021
 */

@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PlayerController playerController;

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Testclass to test a simple post and get request, creation of player "name"
     * @throws Exception
     */
    @Test
    public void testPostandGet() throws Exception {
        String uri_check = "/playernames";
        String uri_name = "/players/name";

        /**
         * Test successful post request, creation of Player: "name"
         */
        MvcResult mvcResult = askForPostResult(uri_name);
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        /**
         * Test successful get request
         */
        mvcResult = askForGetResult(uri_check);
        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }

    private MvcResult askForPostResult(String uri) throws Exception {
        MvcResult output = mvc.perform(MockMvcRequestBuilders.post(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        return output;
    }

    private MvcResult askForGetResult(String uri) throws Exception {
        MvcResult output = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        return output;
    }

    /**
     * Test to get the player "Name" created in the post and get request test
     * @throws Exception
     */
    @Test
    public void getNamePlayer() throws Exception {
        String uri = "/players/name";
        Player player1 = new Player("name");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE, "application/hal+json")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String response = mvcResult.getResponse().getContentAsString();
        Player player = super.mapFromJson(response, Player.class);
        assertEquals(player.getName(), player1.getName());
    }


}
