package ch.zhaw.dodelidobackend;

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
 * Class to test the end points of the RoundCounter
 * Test the initialization and accessing the RoundCounter
 * Test the incrementation of the RoundCounter
 * Test setting the RoundCounter back to zero
 *
 * @author Lukas Boril
 * @version 2021.05.30
 */
@SpringBootTest
@AutoConfigureMockMvc
public class RoundCounterTest {

    protected MockMvc mvc;
    @Autowired
    private RoundCounterController roundCounterController;
    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testDefaultTimerTime() throws Exception {

        // checks if initialized with value 0
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/roundCounter")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        int int_resp = mapFromJson(response, Integer.class);
        assertEquals(0, int_resp);

        // checks if it can be increased
         mvcResult = mvc.perform(MockMvcRequestBuilders.put("/roundCounter")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
         mvcResult = mvc.perform(MockMvcRequestBuilders.get("/roundCounter")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
         response = mvcResult.getResponse().getContentAsString();
         int_resp = mapFromJson(response, Integer.class);
        assertEquals(1, int_resp);

        // checks if can be set back to 0
        mvcResult = mvc.perform(MockMvcRequestBuilders.post("/roundCounter")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        mvcResult = mvc.perform(MockMvcRequestBuilders.get("/roundCounter")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        response = mvcResult.getResponse().getContentAsString();
        int_resp = mapFromJson(response, Integer.class);
        assertEquals(0, int_resp);

    }



    private <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(json, clazz);
    }

}
