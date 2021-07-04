package ch.zhaw.dodelidobackend;

import ch.zhaw.dodelidobackend.controller.OpenCardController;
import ch.zhaw.dodelidobackend.model.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class to test the REST API as well as the underlying methods called from the service class
 * @author Nadine Duss
 * @version 2021.06.05
 */

public class OpenCardControllerTest  extends AbstractTest {

        @Autowired
        OpenCardController openCardController;

        @Override
        @BeforeEach
        public void setUp() {
            super.setUp();
        }

    /**
     * mock a get request and test whether the response is successful (status).
     * The repsonse is analysed if contains:
     *  - as expected 3 cards
     *  - those cards have an animal and a color enum value
     * @throws Exception
     */
    @Test
        public void getCardList() throws Exception {
        String uri = "/openCards/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String response = mvcResult.getResponse().getContentAsString();

        Card[] cardList = super.mapFromJson(response, Card[].class);
        assertTrue(cardList.length > 0);
        assertTrue(cardList.length == 3);

        assertTrue(Arrays.stream(Card.Animals.values()).anyMatch(animal -> animal == cardList[0].getAnimal()));
        assertTrue(Arrays.stream(Card.Colors.values()).anyMatch(color -> color == cardList[0].getColor()));
        assertTrue(Arrays.stream(Card.Animals.values()).anyMatch(animal -> animal == cardList[1].getAnimal()));
        assertTrue(Arrays.stream(Card.Colors.values()).anyMatch(color -> color == cardList[1].getColor()));
        assertTrue(Arrays.stream(Card.Animals.values()).anyMatch(animal -> animal == cardList[2].getAnimal()));
        assertTrue(Arrays.stream(Card.Colors.values()).anyMatch(color -> color == cardList[2].getColor()));

    }

    /**
     * mock two sequential get requests and check whether only one card was changed. As the replacement card
     * could be the identical card two or three cards of the initial request should stay the same
     * @throws Exception
     */
    @Test
    public void replaceCard() throws Exception {
        String uri = "/openCards/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
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



