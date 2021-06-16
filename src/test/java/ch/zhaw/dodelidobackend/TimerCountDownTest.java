package ch.zhaw.dodelidobackend;

import ch.zhaw.dodelidobackend.controller.TimerCountDownController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class to test the end points of the TimerController
 * Test the setting of new count down values
 * Test the starting and the checking of a countdown
 *
 * @author Lukas Boril
 * @version 2021.05.30
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TimerCountDownTest {

    protected MockMvc mvc;
    @Autowired
    private TimerCountDownController timerCountDownController;
    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testDefaultTimerTime() throws Exception {
        String uri_go = "/timer/go";
        String uri_check = "/timer/check";

        // check timer before
        MvcResult mvcResult = askForResult(uri_check);
        String answer = mvcResult.getResponse().getContentAsString();
        assertEquals("false", answer);

        // start timer
        mvcResult = askForResult(uri_go);
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        // check timer just after start
        mvcResult = askForResult(uri_check);
        answer= mvcResult.getResponse().getContentAsString();
        assertEquals("false", answer);

         // sleep for 11 sec, so the default timer of 10 sec is done
        try {
            Thread.currentThread().sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // check timer after it finished
        mvcResult = askForResult(uri_check);
        answer= mvcResult.getResponse().getContentAsString();
        assertEquals("true", answer);
    }


    @Test
    public void testSettingOfNewTimes() throws Exception {
        // set new timer which is out of bound and thus should be denied
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/timer/setTo/200")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Integer int_resp = mapFromJson(response, Integer.class);
        assertEquals(0, int_resp);

        // set new timer within the accepted range
        mvcResult = mvc.perform(MockMvcRequestBuilders.put("/timer/setTo/10")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        response = mvcResult.getResponse().getContentAsString();
        int_resp = mapFromJson(response, Integer.class);
        assertEquals(1, int_resp);

    }




    private MvcResult askForResult(String uri) throws Exception {
        MvcResult output = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
                return output;
    }

    private <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(json, clazz);
    }


}
