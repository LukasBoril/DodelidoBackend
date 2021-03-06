package ch.zhaw.dodelidobackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.zhaw.dodelidobackend.controller.PlayerController;
import ch.zhaw.dodelidobackend.model.Player;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.*;
import org.junit.runner.manipulation.Alphanumeric;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Test class for PlayerController
 *
 * @author Kaltrim Bajrami
 * @version 01.07.2021
 */

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
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
     *
     * @throws Exception
     */
    @Test
    public void t1testPostAndGet() throws Exception {
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


    /**
     * Test to get the player "Name" created in the post and get request test
     *
     * @throws Exception
     * @result the test is successful if the player with the name "name" was created
     */
    @Test
    public void t2getNamePlayer() throws Exception {
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

    /**
     * tests initial yourTurn value after creating the player
     * @throws Exception
     * @result successful test means that player value yourTurn is false
     */
    @Test
    public void t3getNamePlayerInitTurnValue() throws Exception {
        String uri = "/players/name";
        Player player1 = new Player("name");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE, "application/hal+json")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String response = mvcResult.getResponse().getContentAsString();
        Player player = super.mapFromJson(response, Player.class);
        assertEquals(player.getYourTurn(), player1.getYourTurn());
    }

    /**
     * Test for the start of the game
     * @throws Exception
     * @result test is successful if after the start the created player "name" has the yourTurn value true
     */
    @Test
    public void t4StartGame() throws Exception {
        String uri = "/players/name";
        Player player1 = new Player("name");
        player1.setYourTurn(true);
        startRequest("/start");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE, "application/hal+json")).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String response = mvcResult.getResponse().getContentAsString();
        Player player = super.mapFromJson(response, Player.class);
        assertEquals(player.getYourTurn(), player1.getYourTurn());

    }

    /**
     * create a get request with return of the result
     * @param uri
     * @return MvcResult (Json)
     * @throws Exception
     */
    private MvcResult askForGetResult(String uri) throws Exception {
        MvcResult output = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        return output;
    }

    /**
     * create a post request with return of the result
     * @param uri
     * @return MvcResult (Json)
     * @throws Exception
     */
    private MvcResult askForPostResult(String uri) throws Exception {
        MvcResult output = mvc.perform(MockMvcRequestBuilders.post(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        return output;
    }

    /**
     * create a get request with return of the result, triggers game start
     * @param uri
     * @return MvcResult (Json)
     * @throws Exception
     */
    private void startRequest(String uri) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE));
    }


}
