package ch.zhaw.dodelidobackend;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

    import java.util.Arrays;

    import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@ContextConfiguration(classes = OpenCardController.class)
@ContextConfiguration(classes = DodelidoBackendApplication.class)
public class OpenCardControllerTest  extends AbstractTest {

        @Autowired
       OpenCardController openCardController;

        @Override
        @BeforeEach
        public void setUp() {
            super.setUp();
        }

        @Test
        public void getCardList() throws Exception {
            String uri = "/openCards/";
            MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

            int status = mvcResult.getResponse().getStatus();
            assertEquals(200, status);
            String response = mvcResult.getResponse().getContentAsString();

            Card[] cardList = super.mapFromJson(response, Card[].class);
            assertTrue(cardList.length > 0);
//            assertEquals(customerList[1].getColor(), Card.Colors);
            assertTrue(Arrays.stream(Card.Animals.values()).anyMatch(animal -> animal == cardList[0].getAnimal() ));
            //assertThat(result, contains("Adam","Alexander"))   import of hamcrest needed

        }

    @Test
    public void replaceCard() throws Exception {
        String uri = "/openCards/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String response = mvcResult.getResponse().getContentAsString();

        Card[] cardList = super.mapFromJson(response, Card[].class);
        assertTrue(cardList.length > 0);

        //request new card deck with one replace card
        mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String responseReplacement = mvcResult.getResponse().getContentAsString();

        Card[] cardListReplacement = super.mapFromJson(responseReplacement, Card[].class);
        assertTrue(cardListReplacement.length > 0);
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            if(cardList[i].equals(cardListReplacement[i])) {
                sum++;
            }
        }
        //one card is replaced, 2 card should stay the same. it can happen that
        // the replacement card is identical to its predecessor.
        assertTrue(sum >= 2);
    }

    }



